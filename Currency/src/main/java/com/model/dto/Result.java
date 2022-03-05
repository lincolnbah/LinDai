package com.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Result<Object> implements Serializable {
    private Map status;
    private Object data;

    public static <Object> Result success() {
        return Result.success(null);
    }

    public static <Object> Result success(Object data) {
        Result<Object> result = new Result<Object>();
        HashMap map = new HashMap<>();
        map.put("code", StatusCodeEnum.success.getCode());
        map.put("mag", StatusCodeEnum.success.getMsg());
        result.setStatus(map);
        result.setData(data == null ? (Object) "" : data);
        return result;
    }
}
