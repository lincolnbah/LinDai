package com.controller;

import com.model.dto.Result;
import com.model.po.Currency;
import com.model.service.DataProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class CRUD {
    @Autowired
    DataProcessing dataProcessing;

    // 查全部
    @ResponseBody
    @RequestMapping("/searchAll")
    public Result getCoindeskAll() throws ParseException {
        return Result.success(dataProcessing.getAllData());
    }

    // 查最新一筆
    @ResponseBody
    @RequestMapping("/searchLatest")
    public Result getCoindeskLatest() throws ParseException {
        return Result.success(dataProcessing.getLatestData());
    }

    // 用時間查尋
    @ResponseBody
    @RequestMapping("/searchToDateTime")
    public Result getCoindeskLatest(@RequestParam String dateTime) throws ParseException {
        return Result.success(dataProcessing.getDataToDatetime(dateTime));
    }

    // 刪除資料
    @ResponseBody
    @PostMapping("/delete")
    public Result deleteData(@RequestParam String dateTime) throws ParseException {
        return Result.success(dataProcessing.deleteData(dateTime));
    }

    // 新增資料
    @ResponseBody
    @PostMapping("/insert")
    public Result insertData(@RequestBody Currency currency) throws ParseException {
        dataProcessing.insertData(currency);
        return Result.success();
    }

    // 修改
    @ResponseBody
    @PostMapping("/update")
    public Result updateData(@RequestBody Currency currency) throws ParseException {
        dataProcessing.updateData(currency);
        return Result.success();
    }
}
