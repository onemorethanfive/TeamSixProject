package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.BillTagMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.czbank.easylife.model.Bill;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BillMapper {
    @Insert("INSERT INTO bill (bill_id,bill_type,bill_num,bill_date,bill_tag,sign,sign_type,bill_remarks) VALUES (#{billID},#{billType},#{billNum},#{billDate},#{billTag},#{sign},#{signType},#{billRemarks});")
    public void addBill(@Param("billID") String billId,@Param("billType") String billType,@Param("billNum") String billNum,@Param("billDate") String billDate,@Param("billTag") String billTag,@Param("sign") String sign,@Param("signType") String signType,@Param("billRemarks") String billRemarks);

    @Select("SELECT * FROM bill WHERE bill_id = #{billId};")
    public String getCardMoneyById(@Param("billId") String billId);

    @Select("SELECT * FROM bill WHERE bill_id IN (SELECT bill_id FROM bill_card_map WHERE card_id IN (SELECT card_id FROM card_user_map WHERE user_id = #{userID}));")
    public List<Bill> getBillByUserId(@Param("userID") String userID);

    @Insert("INSERT INTO card (card_id,card_psw,card_time,card_loc,card_money,card_type) VALUES (#{cardId},#{cardPsw},#{cardTime},#{cardLoc},#{cardMoney}),#{cardType});")
    public void addCard(@Param("cardId") String cardId, @Param("cardPsw") String cardPsw, @Param("cardTime") String cardTime, @Param("cardLoc") String cardLoc, @Param("cardMoney") String cardMoney, @Param("cardType") String cardType);

    @Select("SELECT SUM(bill_num) bill_number,bill_tag FROM bill a WHERE cast(a.bill_date AS UNSIGNED INTEGER)>#{month} AND a.bill_type='1' GROUP BY a.bill_tag;")
    public List<BillTagMap> getBillByMonthUserId(@Param("month") BigInteger month, @Param("userID") String userID);

    @Delete("DELETE FROM card WHERE card_id = #{cardId};")
    public void removeCard(@Param("cardId") String cardId);

    @Update("Update card SET card_money = #{cardMoney} WHERE card_id = #{cardId};")
    public void updateCard(@Param("cardId") String cardId);

    @Select("SELECT  a.* FROM bill a,bill_card_map b ,card_user_map c WHERE a.bill_id = b.bill_id AND b.card_id = c.card_id AND c.user_id =#{userId} AND CAST(a.bill_date AS UNSIGNED INTEGER) > CAST(#{billDate} AS UNSIGNED INTEGER)*100000000;")
    public List<Bill> findBillsByDateUser(@Param("billDate") String billDate,@Param("userId") String userId);
    /*传入查询的日期，查询从该日期到当前日期的所有账单,
    用当前日期的余额反推算到指定日期的每日余额*/

    
    @Select("SELECT * FROM bill WHERE bill_id IN (SELECT bill_id FROM bill_card_map WHERE card_id IN (SELECT card_id FROM card_user_map WHERE user_id = #{userID})) AND bill_tag = #{billTag};")
    public List<Bill> getBillByUserIdAndTag(@Param("userID") String userID,@Param("billTag") String billTag);



}
