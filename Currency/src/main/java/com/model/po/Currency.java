package com.model.po;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "eur_chinese")
    private String eurChinese;
    @Column(name = "eur_english")
    private String eurEnglish;
    @Column(name = "eur_rate")
    private BigDecimal eurRate;
    @Column(name = "gbp_chinese")
    private String gbpChinese;
    @Column(name = "gbp_english")
    private String gbpEnglish;
    @Column(name = "gbp_rate")
    private BigDecimal gbpRate;
    @Column(name = "usd_chinese")
    private String usdChinese;
    @Column(name = "usd_english")
    private String usdEnglish;
    @Column(name = "usd_rate")
    private BigDecimal usdRate;
    @Column(name = "update_time")
    private Date updateTime;
}
