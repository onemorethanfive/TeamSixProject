package com.czbank.easylife.model;

import lombok.Data;

@Data
public class PiggyBag {
    private String moneyPool;
    private String dailyMoney;
    private String targetMoney;
    private String userId;
    private String startDate;
    private String endDate;
}