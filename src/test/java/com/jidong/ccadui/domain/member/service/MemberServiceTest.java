package com.jidong.ccadui.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jidong.ccadui.domain.member.repository.MbMemOauth;
import com.jidong.ccadui.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("queryDsl로 DTO 변환한 결과 제대로 불러오는지 테스트")
    void get_DTO_Test() {

        //given
        memberRepository.save(MbMemOauth.builder()
                .memNo(2000)
                .svcNm("SERVICE_TEST")
                .svcUsrId("kakao_profile_nickname2")
                .build());

        MemberOAuth memberOAuth = memberService.getMemberInfo(2000);

        assertEquals(memberOAuth.getServiceName(), "SERVICE_TEST");
        assertEquals(memberOAuth.getServiceUserId(), "kakao_profile_nickname2");
    }

    @Test
    void insert_Member_Test() {
        //given
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setMemberNo(2001);
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("jibab");

        //then
        memberService.insertMember(memberOAuth);

        //when
        MemberOAuth memberOAuth2 = memberService.getMemberInfo(2001);
        assertEquals(memberOAuth2.getServiceName(), "kakao");
        assertEquals(memberOAuth2.getServiceUserId(), "jibab");
    }
}