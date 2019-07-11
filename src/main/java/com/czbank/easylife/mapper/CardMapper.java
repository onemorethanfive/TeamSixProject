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

    @Insert("INSERT INTO card (card_id,card_psw,card_time,card_loc,card_money,card_type) VALUES (#{cardId},#{cardPsw},#{cardTime},#{cardLoc},#{cardMoney}),#{cardType});")
    public void addCard(@Param("cardId") String cardId,@Param("cardPsw") String cardPsw,@Param("cardTime") String cardTime,@Param("cardLoc") String cardLoc,@Param("cardMoney") String cardMoney,@Param("cardType") String cardType);

    @Delete("DELETE FROM card WHERE card_id = #{cardId};")
    public void removeCard(@Param("cardId") String cardId);

    @Update("Update card SET card_money = #{cardMoney} WHERE card_id = #{cardId};")
    public void updateCard(@Param("cardId") String cardId);
}
