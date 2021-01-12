package com.jidong.ccadui.common;

import java.util.EnumSet;

public class EnumUtils {
    public static <T extends Enum<T>> T findEnumByCode(Class<T> enumClass, String code) {
          return EnumSet.allOf(enumClass).stream()
                .filter(type -> type.equals(code))
                .findFirst()
                .orElseGet(null);
       }
}
