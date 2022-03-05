package com.model.service;

import com.model.dto.Result;
import com.model.dto.StatusCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Object> handle(Exception e) {
        Result<Object> result = new Result<Object>();
        HashMap map = new HashMap<>();
        map.put("code", StatusCodeEnum.error.getCode());
        map.put("mag", StatusCodeEnum.error.getMsg());
        result.setStatus(map);
        result.setData("");
        return result;
    }
}
