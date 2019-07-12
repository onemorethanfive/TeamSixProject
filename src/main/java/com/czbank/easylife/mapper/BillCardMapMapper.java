package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BillCardMapMapper {
    @Select("SELECT a.* FROM bill a , bill_card_map b WHERE a.bill_id = b.bill_id AND b.card_id = #{cardId};")
    public List<Bill> findBillsByCard(@Param("cardId") String cardId);

    @Insert("INSERT INTO bill_card_map (card_id,user_id,bill_eztag) VALUES (#{cardId},#{userId},#{billEztag};")
    public void addBillIntoMap(@Param("cardId") String cardId, @Param("billId") String billId,@Param("billEztag")String billEztag);

}
