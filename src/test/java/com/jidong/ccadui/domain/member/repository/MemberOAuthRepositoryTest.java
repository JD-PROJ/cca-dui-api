package com.jidong.ccadui.domain.member.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberOAuthRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("entityManager insert 적용 테스트")
    void entityManager_insert_Test() {
        //given
        LocalDateTime now = LocalDateTime.now();
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("jibab");
        memberOAuth.setCreateDate(now);
        memberOAuth.setUpdateDate(now);

        //then
        memberRepository.insertMember(memberOAuth);

        //when
        MemberOAuth memberOAuth2 = memberRepository.getMemberInfoByServiceUserId("jibab");

        assertEquals(memberOAuth2.getServiceName(), "kakao");
        assertEquals(memberOAuth2.getServiceUserId(), "jibab");

        assertNotNull(memberOAuth2.getCreateDate());
        System.out.println("createDt : " + memberOAuth2.getCreateDate());
        System.out.println("updateDt : " + memberOAuth2.getUpdateDate());
    }
}