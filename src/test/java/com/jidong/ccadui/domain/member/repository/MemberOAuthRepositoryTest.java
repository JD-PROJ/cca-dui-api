package com.jidong.ccadui.domain.member.repository;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberOAuthRepositoryTest {

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
    void create_Enitity_Test() {
        //given
        memberRepository.save(MbMemOAuth.builder()
                .svcNm("kakao")
                .svcUsrId("kakao_profile_nickname")
                .build());

        //when
        List<MbMemOAuth> mbMemOAuthList = memberRepository.findAll();

        //then
        MbMemOAuth mbMemOAuth = mbMemOAuthList.get(0);

        assertEquals(mbMemOAuth.getMemNo(), 1);
        assertEquals(mbMemOAuth.getSvcNm(), "kakao");
        assertEquals(mbMemOAuth.getSvcUsrId(), "kakao_profile_nickname");
        assertTrue(mbMemOAuth.getCreateDt().isBefore(now()));
    }

    @Test
    @DisplayName("queryDsl 적용 테스트")
    void queryDsl_Test() {
        //given
        memberRepository.save(MbMemOAuth.builder()
                .svcNm("kakao2")
                .svcUsrId("kakao_profile_nickname2")
                .build());

        //when
        MemberOAuth memberOAuth = memberRepository.getMemberInfo(1);

        //then

        assertEquals(memberOAuth.getServiceName(), "kakao2");
        assertEquals(memberOAuth.getServiceUserId(), "kakao_profile_nickname2");
    }

    @Test
    @Transactional
    @DisplayName("entityManager insert 적용 테스트")
    void entityManager_insert_Test() {
        //given
        MemberOAuth memberOAuth = new MemberOAuth();
        memberOAuth.setMemberNo(1L);
        memberOAuth.setServiceName("kakao");
        memberOAuth.setServiceUserId("jibab");

        //then
        memberRepository.insertMember(memberOAuth);
    }
}