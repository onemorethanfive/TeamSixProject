package com.czbank.easylife.mapper;
import com.czbank.easylife.model.UserSpend;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserSpendMapper {
    @Select("SELECT * FROM user_spend WHERE userid=#{userId} AND date=(SELECT MAX(date) FROM user_spend WHERE date<#{date});")
    public UserSpend findTotalspendByIdAndDate(@Param("userId") String userId, @Param("date") String date);
    @Update("UPDATE datetotal SET datetotal = #{datetotal} AND limittotal = #{limittotal} where bill_id = #{billId}")
    public int updateUserSpend(@Param("billId") String billId, @Param("datetotal") String datetotal, @Param("limittotal") String limittotal);
}
