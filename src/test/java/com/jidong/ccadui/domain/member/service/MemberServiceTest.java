package com.jidong.ccadui.domain.member.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jidong.ccadui.domain.member.repository.MbMemOAuth;
import com.jidong.ccadui.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void set_member() {
        //given
        memberRepository.save(MbMemOAuth.builder()
                .svcNm("kakao2")
                .svcUsrId("kakao_profile_nickname2")
                .build());
    }

    @Test
    @DisplayName("queryDsl로 DTO 변환한 결과 제대로 불러오는지 테스트")
    void get_DTO_Test() {
        memberOAuth = memberService.getMemberInfo(1L);

        assertEquals(memberOAuth.getServiceName(), "kakao2");
        assertEquals(memberOAuth.getServiceUserId(), "kakao_profile_nickname2");
    }
}