package com.czbank.easylife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {
    private String householdId;
    private String userId;
    private String householdName;
    private String householdNum;
    private String householdLoc;
}
