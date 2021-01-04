package com.jidong.ccadui.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jidong.ccadui.domain.member.repository.MbMemOauth;
import com.jidong.ccadui.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private MemberOAuth memberOAuth;

    @Test
    @DisplayName("queryDsl로 DTO 변환한 결과 제대로 불러오는지 테스트")
    void get_DTO_Test() {

        //given
        memberRepository.save(MbMemOauth.builder()
                .svcNm("kakao2")
                .svcUsrId("kakao_profile_nickname2")
                .build());

        memberOAuth = memberService.getMemberInfo(1L);

        assertEquals(memberOAuth.getServiceName(), "kakao2");
        assertEquals(memberOAuth.getServiceUserId(), "kakao_profile_nickname2");
    }

    @Test
    void insert_Member_Test() {
        //given
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setMemberNo(2L);
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("jibab");

        memberService.insertMember(memberOAuth);

        MemberOAuth memberOAuth2 = memberService.getMemberInfo(2);
        assertEquals(memberOAuth2.getServiceName(), "kakao");
        assertEquals(memberOAuth2.getServiceUserId(), "jibab");
    }
}