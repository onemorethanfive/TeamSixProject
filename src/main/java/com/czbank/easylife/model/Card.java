package com.czbank.easylife.model;

import lombok.Data;

@Data
public class Card {
    private String cardId;
    private String cardNumber;
    private String cardPsw;
    private String cardTime;
    private String cardLoc;
    private String cardMoney;
    private String cardType;

}