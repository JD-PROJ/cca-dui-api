package com.jidong.ccadui.controller.api.dto;

import lombok.Data;
import net.sf.json.JSONObject;

@Data
public class KakaoAPIResultVO {
    private String code;
    private String message;
    private JSONObject result;

    public KakaoAPIResultVO(KakaoLoginResultEnum kakaoLoginResultEnum) {
        this.code = kakaoLoginResultEnum.getCode();
        this.message = kakaoLoginResultEnum.getMessage();
    }

    public KakaoAPIResultVO(KakaoLoginResultEnum kakaoLoginResultEnum, JSONObject result) {
        this.code = kakaoLoginResultEnum.getCode();
        this.message = kakaoLoginResultEnum.getMessage();
        this.result = result;
    }
}
