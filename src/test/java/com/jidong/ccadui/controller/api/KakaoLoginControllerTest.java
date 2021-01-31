package com.jidong.ccadui.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jidong.ccadui.controller.api.dto.KakaoLoginResultV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KakaoLoginControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("kakao login 및 jwt token 생성 컨트롤러 테스트")
    void kakaoLoginControllerTest() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(header);

        ResponseEntity<KakaoLoginResultV1> responseEntity =
                this.testRestTemplate.exchange("/api/login/kakao?accessToken=" +
                                "LLs_KXCZyvF2UhyzhU9WKZS1kXgRRRj_OpHOnQo9JkAAAF2-7kfoQ",
                        HttpMethod.GET, requestEntity,
                        new ParameterizedTypeReference<KakaoLoginResultV1>() {
                        });

        KakaoLoginResultV1 kakaoLoginResultV1 = responseEntity.getBody();

//        assertNotNull(kakaoLoginResultV1.getResultCode());
//        assertEquals("401", kakaoLoginResultV1.getResultCode());
    }
}