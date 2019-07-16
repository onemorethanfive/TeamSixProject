package com.czbank.easylife.model;

import lombok.Data;

@Data
public class UserSpend {
    private String key;
    private String userid;
    private String totalspend;
    private String date;
}
