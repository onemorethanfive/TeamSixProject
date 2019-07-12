package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CardUserMapMapper {
    @Select("SELECT a.* FROM card a , card_user_map b WHERE a.card_id = b.card_id AND b.user_id = #{userId};")
    public List<Card> findCardsByUser(@Param("userId") String userId);

    @Insert("INSERT INTO card_user_map (card_id,user_id) VALUES (#{cardId},#{userId};")
    public void addCardIntoMap(@Param("cardId") String cardId, @Param("userId") String userId);

    @Delete("DELETE FROM card_user_map WHERE card_id = #{cardId};")
    public void removeCardFromMap(@Param("cardId") String cardId);

}
