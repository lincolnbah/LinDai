資料轉換(打API將資料存DB)
API-POST(http://localhost:8080/latestData)
-------------------------------------------------------
查詢(全部)
API-GET(http://localhost:8080/searchAll)
查詢(最新一筆)
API-GET(http://localhost:8080/searchLatest)
查詢(時間查詢)
API-GET(http://localhost:8080/searchToDateTime)
request:
dateTime (yyyy-MM-dd HH:mm)
-------------------------------------------------------
新增
API-POST(http://localhost:8080/insert)
request:
{
  "eurChinese": "歐元",
  "eurEnglish": "Euro",
  "eurRate": 35932.878900,
  "gbpChinese": "英鎊",
  "gbpEnglish": "British Pound Sterling",
  "gbpRate": 29692.348800,
  "usdChinese": "美金",
  "usdEnglish": "United States Dollar",
  "usdRate": 39275.541700
}
-------------------------------------------------------
刪除
API-POST(http://localhost:8080/delete)
request
dateTime (yyyy-MM-dd HH:mm)
-------------------------------------------------------
修改
API-POST(http://localhost:8080/update)
request
{
  "eurChinese": "歐元",
  "eurEnglish": "Euro",
  "eurRate": 35932.878900,
  "gbpChinese": "英鎊",
  "gbpEnglish": "British Pound Sterling",
  "gbpRate": 29692.348800,
  "usdChinese": "美金",
  "usdEnglish": "United States Dollar",
  "usdRate": 39275.541700,
  "updateTime": "2022-03-05T03:11:00.000+00:00"
}
說明:修改某筆updateTime為參數值的整筆資料
-------------------------------------------------------
SQL:
CREATE TABLE IF NOT EXISTS `currencytt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eur_chinese` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `eur_english` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `eur_rate` decimal(20,6) DEFAULT NULL,
  `gbp_chinese` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `gbp_english` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `gbp_rate` decimal(20,6) DEFAULT NULL,
  `usd_chinese` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `usd_english` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `usd_rate` decimal(20,6) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)
