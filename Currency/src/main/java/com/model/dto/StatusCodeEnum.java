package com.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCodeEnum {
    success(200, "success"),
    error(400, "error");

    private final Integer code;
    private final String msg;
    }
