package com.jidong.ccadui.common;

import com.fasterxml.jackson.annotation.JsonValue;

public interface EnumType {
    String getCode();

    @JsonValue
    default String toValue() {
        return getCode();
    }
}
