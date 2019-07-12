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


    public Card updateCard(String cardId,String cardPsw,String cardMoney){
        Card card=cardMapper.findCardById(cardId);
        return card;
    }

}
