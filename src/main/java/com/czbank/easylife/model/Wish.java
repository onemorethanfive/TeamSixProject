package com.czbank.easylife.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wish {
    private String wishId;
    private String userId;
    private String wishName;
    private String wishNum;
    private String starDate;
    private String targetDate;
    private String wishState;
    private String currentState;
    private String wishRemark;
    private String interestRate;
    private String interestId;
    private String wishBillId;
}
