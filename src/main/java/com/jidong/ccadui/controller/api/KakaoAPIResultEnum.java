package com.jidong.ccadui.controller.api;

public enum KakaoAPIResultEnum {
    SUCCESS("200", "성공"),
    NOT_VALID_TOKEN("400", "토큰정보가 잘못되었습니다."),
    TOKEN_EXPIRED("401", "토큰정보가 만료되었습니다."),
    KAKAO_ERROR("-1", "카카오 에러")
    ;

    private final String code;
    private final String message;

    KakaoAPIResultEnum(String code, String message) {
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
}
