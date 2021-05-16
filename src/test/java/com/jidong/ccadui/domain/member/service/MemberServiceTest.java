package com.jidong.ccadui.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jidong.ccadui.domain.member.repository.MemberRepository;
import java.util.Optional;
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
    void insert_Member_Test() {
        //given
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("123456");
        memberOAuth.setServiceProfileId("jibab");

        //then
        memberService.insertMember(memberOAuth);

        //when
        MemberOAuth memberOAuth2 = memberService.getMemberInfoByServiceUserId("123456");
        assertEquals(memberOAuth2.getServiceName(), "kakao");
        assertEquals(memberOAuth2.getServiceUserId(), "123456");
        assertNotNull(memberOAuth2.getCreateDate());
    }
}