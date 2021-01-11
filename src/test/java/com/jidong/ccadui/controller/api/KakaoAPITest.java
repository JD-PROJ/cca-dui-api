package com.jidong.ccadui.controller.api;

import static org.junit.jupiter.api.Assertions.*;

import net.sf.json.JSONObject;
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

        JSONObject jsonObject = kakaoAPI.getKaKaoUserInfo(accessToken);

        System.out.println("jsonobject : " + jsonObject.toString());
        assertEquals("-401", jsonObject.optString("code"));
    }

    @Test
    @DisplayName("잘못된 accessToken으로 kakao API 호출 테스트")
    public void KAKAO_getUserInfo_API_TEST2() {
        String accessToken = "";

        JSONObject jsonObject = kakaoAPI.getKaKaoUserInfo(accessToken);

        System.out.println("jsonobject : " + jsonObject.toString());
        assertEquals("-401", jsonObject.optString("code"));
    }

}