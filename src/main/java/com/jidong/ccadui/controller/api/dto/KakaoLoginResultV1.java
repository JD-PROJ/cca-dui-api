package com.jidong.ccadui.controller.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KakaoLoginResultV1 {
    @ApiModelProperty("결과 코드")
    private String resultCode;

    @ApiModelProperty("결과 메세지")
    private String resultMessage;

    @ApiModelProperty("jwt Token")
    private String jwtToken;

    public KakaoLoginResultV1(KakaoLoginResultEnum kakaoLoginResultEnum) {
        this.resultCode = kakaoLoginResultEnum.getCode();
       this.resultMessage = kakaoLoginResultEnum.getMessage();
    }

    public KakaoLoginResultV1(KakaoLoginResultEnum kakaoLoginResultEnum, String jwtToken) {
        this.resultCode = kakaoLoginResultEnum.getCode();
        this.resultMessage = kakaoLoginResultEnum.getMessage();
        this.jwtToken = jwtToken;
    }
}
