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

    @Insert("INSERT INTO bill_card_map (bill_id,card_id,bill_eztag) VALUES (#{billId},#{cardId},#{billEztag});")
    public int addBillIntoMap(@Param("billId") String billId, @Param("cardId") String cardId,@Param("billEztag")String billEztag);

    @Update("UPDATE bill_card_map SET bill_eztag = #{billEZTag} where bill_id = #{billId}")
    public int updateTag(@Param("billId") String billId, @Param("billEZTag") String billEZTag);

}
