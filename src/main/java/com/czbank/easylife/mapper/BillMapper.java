package com.czbank.easylife.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BillMapper {
    @Insert("INSERT INTO bill (bill_id,bill_type,bill_num,bill_date,bill_tag,sign,sign_type,bill_remarks) VALUES (#{billId},#{billType},#{billNum},#{billDate},#{billTag},#{sign},#{signType},#{billRemarks});")
    public void addBill(@Param("billID") String billId,@Param("billType") String billType,@Param("billNum") String billNum,@Param("billDate") String billDate,@Param("billTag") String billTag,@Param("sign") String sign,@Param("signType") String signType,@Param("billRemarks") String billRemarks);
    @Select("SELECT * FROM bill WHERE bill_id = #{billId};")
    public String getCardMoneyById(@Param("billId") String billId);
}
