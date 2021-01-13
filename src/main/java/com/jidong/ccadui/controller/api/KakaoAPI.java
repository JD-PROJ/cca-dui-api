package com.jidong.ccadui.controller.api;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class KakaoAPI {


    public HttpResponse getKaKaoUserInfo(String accessToken) {
        // 카카오 userInfo 받아오기
        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);

        post.addHeader("Authorization", "Bearer " + accessToken);

        HttpResponse response = null;

        try {
            response = client.execute(post);

            System.out.println("response getStatusLine : " + response.getStatusLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
