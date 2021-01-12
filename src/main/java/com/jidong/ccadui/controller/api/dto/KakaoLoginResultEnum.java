package com.jidong.ccadui.controller.api.dto;

import com.jidong.ccadui.common.EnumUtils;
import java.util.Arrays;
import java.util.function.Supplier;

public enum KakaoLoginResultEnum {
    // kakao api result
    SUCCESS("200", "성공"),
    NOT_VALID_TOKEN("400", "토큰정보가 잘못되었습니다."),
    TOKEN_EXPIRED("401", "토큰정보가 만료되었습니다."),
    KAKAO_ERROR("-1", "카카오 에러"),

    // internal error result
    JWT_TOKEN_GENERATE_ERROR("402", "jwt 토큰 생성에 실패했습니다."),
    BAT_REQUEST_ERROR("403", "요청정보가 잘못됐습니다.")
    ;

    private final String code;
    private final String message;

    KakaoLoginResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    String getCode() {
        return this.code;
    }

    String getMessage() {
        return this.message;
    }

    public boolean equalsCode(String code) {
        return this.code.equals(code);
    }

    public static KakaoLoginResultEnum findByCode(String code) {
        return EnumUtils.findEnumByCode(KakaoLoginResultEnum.class, code);
    }
}
