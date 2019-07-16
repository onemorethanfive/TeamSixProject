package com.czbank.easylife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTag {
    private String billId;
    private String billType;
    private String billNum;
    private String billDate;
    private String billEztag;
    private String billRemarks;
}
