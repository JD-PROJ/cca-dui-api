package com.jidong.ccadui.controller.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class KakaoAPI {

    public JSONObject getKaKaoUserInfo(String access_Token) {
        // 카카오 userInfo 받아오기
        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);

        post.addHeader("Authorization", "Bearer " + access_Token);
        JSONObject returnNode = null;

        HttpResponse response = null;
        String status = "";
        JSONObject result = null;

        try {
            response = client.execute(post);
            HttpEntity entity = response.getEntity();

            System.out.println("response getStatusLine : " + response.getStatusLine());
            System.out.println("response getContent : " + entity.toString());

            String jsonResponse = EntityUtils.toString(entity, "UTF-8");
            if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                result = JSONObject.fromString(jsonResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
