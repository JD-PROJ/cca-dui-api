package com.jidong.ccadui.domain.member.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanUp() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때 마다 respository 전체 비우는 코드.
         메소드 단위인가?
         **/

        memberRepository.deleteAll();
    }

    @Test
    void enitity생성후_회원정보가져오기() {
        //given
        memberRepository.save(Member.builder()
                .serviceName("kakao")
                .serviceUserId("kakao_profile_nickname")
                .build());

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);

        assertEquals(member.getMemNo(), 1);
        assertEquals(member.getServiceName(), "kakao");
        assertEquals(member.getServiceUserId(), "kakao_profile_nickname");

    }

}