package com.czbank.easylife.service;

import com.czbank.easylife.mapper.CardMapper;
import com.czbank.easylife.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardMapper cardMapper;

    public String getMoneyByUser(String userId){
        double money =cardMapper.getMoneyByUser(userId);
        money = money /100;
        String a = money+"";
        return a;
    }

    public Card addCard(Card card){
        cardMapper.addCard(card);
        return card;
    }

    public List<Card> getCardByUser(String userId){
        List<Card> cards=cardMapper.getCardMoneyByUser(userId);
        return cards;
    }

    public Card updateCard(String cardId,String cardPsw,String cardMoney){
        Card card=cardMapper.findCardById(cardId);
        return card;
    }

}
