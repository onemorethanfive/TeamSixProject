package com.czbank.easylife.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String userId;
    private String userName;
    private String userPsw;
    private String idNum;
    private String userGender;
    private String userLoc;
    private String userBudget;
}
