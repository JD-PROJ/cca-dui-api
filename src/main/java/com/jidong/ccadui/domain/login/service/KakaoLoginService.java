package com.jidong.ccadui.domain.login.service;

import com.jidong.ccadui.controller.api.KakaoAPI;
import com.jidong.ccadui.controller.api.dto.KakaoAPIResultVO;
import com.jidong.ccadui.controller.api.dto.KakaoLoginResultEnum;
import java.io.IOException;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    @NotNull
    private final KakaoAPI kakaoAPI;

    public KakaoAPIResultVO callKakaoUserAPI(String accessToken) {
        HttpResponse response;

        // 1. 토큰이 있으면 kakao User Info 를 가지고 옴.
        if (!StringUtils.isEmpty(accessToken)) {
            response = kakaoAPI.getKaKaoUserInfo(accessToken);
        } else {
            return new KakaoAPIResultVO(KakaoLoginResultEnum.BAT_REQUEST_ERROR);
        }

        HttpEntity entity = response.getEntity();

        // Http 응답 코드
        String responseCode = Integer.toString(response.getStatusLine().getStatusCode());
        String jsonResponse = null;

        try {
            // 2. KAKAO 요청결과 json Response 가져옴
            jsonResponse = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. 성공인 경우에만 API 호출 결과 반환함.
        if (KakaoLoginResultEnum.SUCCESS.equalsCode(responseCode)) {
            JSONObject result = JSONObject.fromString(jsonResponse);
            return new KakaoAPIResultVO(KakaoLoginResultEnum.SUCCESS, result);
        }

        // 4. Kakao userInfo 가져오기 실패인 경우엔 응답결과만 전달하고 JSON 결과는 반환하지 않음. (로그로만 남긴다)
        // 카카오 API Error Response JSON 상세 내용
        if(KakaoLoginResultEnum.NOT_VALID_TOKEN.equalsCode(responseCode) ||
            KakaoLoginResultEnum.TOKEN_EXPIRED.equalsCode(responseCode)) {
            log.error("KAKAO API Response Error MESSAGE : " + JSONObject.fromString(jsonResponse));
            return new KakaoAPIResultVO(KakaoLoginResultEnum.findByCode(responseCode));
        }

        // 예상치못한 코드가 내려왔거나 kakao 호출 실패
        return new KakaoAPIResultVO(KakaoLoginResultEnum.INTERNAL_SERVER_ERROR);
    }
}
