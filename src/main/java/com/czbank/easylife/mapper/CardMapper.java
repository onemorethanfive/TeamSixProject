package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CardMapper {
    @Select("SELECT * FROM card WHERE card_id = #{cardId};")
    public Card findCardById(@Param("cardId") String cardId);

    @Select("SELECT card_money FROM card WHERE card_id = #{cardId};")
    public String getCardMoneyById(@Param("cardId") String cardId);

    @Select("SELECT a.* FROM card a,card_user_map b WHERE a.card_id = b.card_id AND b.user_id = #{userId};")
    public List<Card> getCardMoneyByUser(@Param("userId") String userId);

    @Select("SELECT SUM(CAST(a.card_money AS  UNSIGNED INTEGER)) FROM card a,card_user_map b WHERE a.card_id = b.card_id AND b.user_id =#{userId};")
    public double getMoneyByUser(@Param("userId") String userId);

    @Insert("INSERT INTO card (card_id,card_psw,card_time,card_loc,card_money,card_type,card_number) VALUES (#{cardId},#{cardPsw},#{cardTime},#{cardLoc},#{cardMoney}),#{cardType},#{cardNumber});")
    public void addCard(Card card);

    @Delete("DELETE FROM card WHERE card_id = #{cardId};")
    public void removeCard(@Param("cardId") String cardId);

    @Update("Update card SET card_money = #{cardMoney} WHERE card_id = #{cardId};")
    public void updateCard(@Param("cardId") String cardId);
}
