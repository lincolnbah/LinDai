package com.model.service;

import com.model.dao.CurrencyRepository;
import com.model.po.Currency;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Service
public class DataProcessing {

    @Autowired
    CurrencyRepository currencyRepository;

    //    打API 回傳JSON
    public JSONObject returnJSON() {
        String api = "https://api.coindesk.com/v1/bpi/currentprice.json";
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> entity = template.getForEntity(api, String.class);
        return new JSONObject(entity.getBody());
    }

    //    更新資料
    public void changeData(JSONObject jsonObject) throws ParseException {
        String eurChinese = "";
        String eurEnglish = "";
        String eurRate = "";
        String gbpChinese = "";
        String gbpEnglish = "";
        String gbpRate = "";
        String usdChinese = "";
        String usdEnglish = "";
        String usdRate = "";
        Date updateTime = null;

        JSONObject bpi = jsonObject.getJSONObject("bpi");
        String time = jsonObject.getJSONObject("time").getString("updated");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss 'UTC'", Locale.ENGLISH);
        updateTime = simpleDateFormat.parse(time);

        Iterator<String> bpiKeys = bpi.keys();
        while (bpiKeys.hasNext()) {
            String code = bpiKeys.next();
            JSONObject currencyDetail = bpi.getJSONObject(code);
            if (code.equals("EUR")) {
                eurChinese = "歐元";
                eurEnglish = currencyDetail.getString("description");
                eurRate = String.valueOf(currencyDetail.get("rate_float"));
            } else if (code.equals("GBP")) {
                gbpChinese = "英鎊";
                gbpEnglish = currencyDetail.getString("description");
                gbpRate = String.valueOf(currencyDetail.getDouble("rate_float"));
            } else if (code.equals("USD")) {
                usdChinese = "美金";
                usdEnglish = currencyDetail.getString("description");
                usdRate = String.valueOf(currencyDetail.getDouble("rate_float"));
            }
        }

        Currency build = Currency.builder().eurChinese(eurChinese).eurEnglish(eurEnglish).eurRate(new BigDecimal(eurRate))
                .gbpChinese(gbpChinese).gbpEnglish(gbpEnglish).gbpRate(new BigDecimal(gbpRate))
                .usdEnglish(usdEnglish).usdChinese(usdChinese).usdRate(new BigDecimal(usdRate)).updateTime(updateTime).build();
        currencyRepository.save(build);
    }
    // 回傳全部資料
    public List getAllData() {
        return currencyRepository.findAll();
    }
    // 回傳最新一筆資料
    public List getLatestData() {
        return currencyRepository.findLatestData();
    }
    // 時間查找資料
    public List getDataToDatetime(String dateTime) {
        return currencyRepository.getByDate(dateTime);
    }
    // 刪除資料
    public int deleteData(String dateTime) {
        int id = currencyRepository.getByDate(dateTime).get(0).getId();
        return currencyRepository.deleteById(id);
    }
    // 新增資料
    public void insertData(Currency currency) {
        currency.setUpdateTime(new Date());
        currencyRepository.save(currency);
    }
    // 更新資料
    public void updateData(Currency currency) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = dateFormat.format(currency.getUpdateTime());
        int id = currencyRepository.getByDate(dateTime).get(0).getId();
        Currency data = currencyRepository.findById(id).get(0);
        data.setEurChinese(currency.getEurChinese());
        data.setEurEnglish(currency.getEurEnglish());
        data.setEurRate(currency.getEurRate());
        data.setGbpChinese(currency.getGbpChinese());
        data.setGbpEnglish(currency.getGbpEnglish());
        data.setGbpRate(currency.getGbpRate());
        data.setUsdChinese(currency.getUsdChinese());
        data.setUsdEnglish(currency.getUsdEnglish());
        data.setUsdRate(currency.getUsdRate());
        data.setUpdateTime(new Date());
        System.err.println(data);
        currencyRepository.save(data);
    }
}
