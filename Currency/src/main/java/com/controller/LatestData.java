package com.controller;

import com.model.dto.Result;
import com.model.service.DataProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

@Controller
public class LatestData {
    @Autowired
    DataProcessing dataProcessing;

    @ResponseBody
    @PostMapping("/latestData")
    // 資料轉換
    public Result changeData() throws ParseException {
        dataProcessing.changeData(dataProcessing.returnJSON());
        return Result.success();
    }
}
