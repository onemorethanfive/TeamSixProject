package com.czbank.easylife.model;

import lombok.Data;

import java.util.Date;

@Data
public class PiggyBag {
    private String moneyPool;
    private String dailyMoney;
    private String targetMoney;
    private String userId;
    private Date startDate;
    private Date endDate;
}