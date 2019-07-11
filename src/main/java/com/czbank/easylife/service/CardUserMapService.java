package com.czbank.easylife.service;

import com.czbank.easylife.mapper.CardMapper;
import com.czbank.easylife.mapper.CardUserMapMapper;
import com.czbank.easylife.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardUserMapService {
    @Autowired
    private CardUserMapMapper cardUserMapMapper;

    @Autowired
    private CardMapper cardMapper;

    public List<Card> findCardsByUserId(String userId) {
        List<Card> cards = cardUserMapMapper.findCardsByUser(userId);
        return cards;
    }

    public Card addCard(String cardId,String userId){
        cardUserMapMapper.addCardIntoMap(cardId,userId);
        Card card = cardMapper.findCardById(cardId);
        return card;
    }

    public void removeCard(String cardId){
        cardUserMapMapper.removeCardFromMap(cardId);
    }


}
