package com.czbank.easylife.model;

import lombok.Data;

@Data
public class Bill {
    private String billId;
    private String billType;
    private String billNum;
    private String billDate;
    private String billTag;
    private String sign;
    private String signType;
    private String billRemarks;


}