package com.jidong.ccadui.domain.member.repository;


import static org.junit.jupiter.api.Assertions.*;

import com.jidong.ccadui.domain.member.service.MemberOAuth;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        LocalDateTime now = LocalDateTime.now();
        memberRepository.save(MbMemOauth.builder()
                .svcNm("kakao")
                .svcUsrId("kakao_profile_nickname")
                .build());

        //when
        List<MbMemOauth> mbMemOauthList = memberRepository.findAll();

        //then
        MbMemOauth mbMemOAuth = mbMemOauthList.stream().filter(a -> "kakao".equals(a.getSvcNm())).findFirst().orElseThrow(null);

        assertEquals(mbMemOAuth.getMemNo(), 3);
        assertEquals(mbMemOAuth.getSvcNm(), "kakao");
        assertEquals(mbMemOAuth.getSvcUsrId(), "kakao_profile_nickname");
        assertTrue(mbMemOAuth.getCreateDt().isAfter(now));
        assertNotNull(mbMemOAuth.getCreateDt());

        System.out.println("createDt : " + mbMemOAuth.getCreateDt());
        System.out.println("updateDt : " + mbMemOAuth.getUpdateDt());
    }

    @Test
    @DisplayName("queryDsl 적용 테스트")
    void queryDsl_Test() {
        //given
        LocalDateTime now = LocalDateTime.now();

        long memberNo = 1;
        String serviceName = "kakao2";
        String serviceUserId = "kakao_profile_nickname2";
        String serviceUserName = "nickname";
        memberRepository.save(new MbMemOauth(memberNo,serviceName,serviceUserId, serviceUserName));

        //when
        MemberOAuth memberOAuth = memberRepository.getMemberInfo(1);

        //then
        assertEquals("kakao2", memberOAuth.getServiceName());
        assertEquals( "kakao_profile_nickname2", memberOAuth.getServiceUserId());

        //왜널이
        assertNull(memberOAuth.getCreateDate());

    }

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