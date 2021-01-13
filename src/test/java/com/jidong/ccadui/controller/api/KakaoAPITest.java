package com.jidong.ccadui.controller.api;

import static org.junit.jupiter.api.Assertions.*;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoAPITest {

    @Autowired
    KakaoAPI kakaoAPI;

    @Test
    @DisplayName("만료된 accessToken으로 kakao API 호출 테스트")
    public void KAKAO_getUserInfo_API_TEST() {
        String accessToken = "exsjA_8ucH8oLmJhMzYRjxeqM-y_8gNcEY_3ogo9dZsAAAF2538_Mw";

        HttpResponse response = kakaoAPI.getKaKaoUserInfo(accessToken);

        String responseCode = Integer.toString(response.getStatusLine().getStatusCode());
        System.out.println("responseCode : " + responseCode);
        assertEquals("401", responseCode);
    }

    @Test
    @DisplayName("잘못된 accessToken으로 kakao API 호출 테스트")
    public void KAKAO_getUserInfo_API_TEST2() {
        String accessToken = "";

        HttpResponse response = kakaoAPI.getKaKaoUserInfo(accessToken);

        String responseCode = Integer.toString(response.getStatusLine().getStatusCode());
        System.out.println("responseCode : " + responseCode);
        assertEquals("401", responseCode);
    }

}