package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BillMapper {
    @Insert("INSERT INTO bill (bill_id,bill_type,bill_num,bill_date,bill_tag,sign,sign_type,bill_remarks) VALUES (#{billID},#{billType},#{billNum},#{billDate},#{billTag},#{sign},#{signType},#{billRemarks});")
    public void addBill(@Param("billID") String billId,@Param("billType") String billType,@Param("billNum") String billNum,@Param("billDate") String billDate,@Param("billTag") String billTag,@Param("sign") String sign,@Param("signType") String signType,@Param("billRemarks") String billRemarks);
    @Select("SELECT * FROM bill WHERE bill_id = #{billId};")
    public String getCardMoneyById(@Param("billId") String billId);
    @Select("SELECT * FROM bill WHERE bill_id IN (SELECT bill_id FROM bill_card_map WHERE card_id IN (SELECT card_id FROM card_user_map WHERE user_id = #{userID}));")
    public List<Bill> getBillByUserId(@Param("userID") String userID);
}
