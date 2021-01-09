package com.jidong.ccadui.controller.api.dto;

import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public abstract class ResponseV1<T> {

    @NonNull
    private T data;

    private List<String> errors;
}
