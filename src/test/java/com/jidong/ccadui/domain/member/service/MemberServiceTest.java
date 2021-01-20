package com.jidong.ccadui.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jidong.ccadui.domain.Foo;
import com.jidong.ccadui.domain.Person;
import com.jidong.ccadui.domain.member.repository.MbMemOauth;
import com.jidong.ccadui.domain.member.repository.MemberRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                .svcNm("SERVICE_TEST")
                .svcUsrId("kakao_id2")
                .svcUsrNm("nickname")
                .build());

        MemberOAuth memberOAuth = memberService.getMemberInfoByServiceUserId("kakao_id2");

        assertEquals(memberOAuth.getServiceName(), "SERVICE_TEST");
        assertEquals(memberOAuth.getServiceUserId(), "kakao_id2");
    }

    @Test
    void insert_Member_Test() {
        //given
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("123456");
        memberOAuth.setServiceUserName("jibab");

        //then
        memberService.insertMember(memberOAuth);

        //when
        MemberOAuth memberOAuth2 = memberService.getMemberInfoByServiceUserId("123456");
        assertEquals(memberOAuth2.getServiceName(), "kakao");
        assertEquals(memberOAuth2.getServiceUserId(), "123456");
        assertNotNull(memberOAuth2.getCreateDate());
    }
}