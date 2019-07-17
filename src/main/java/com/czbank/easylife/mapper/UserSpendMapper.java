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
    @Update("UPDATE user_spend SET datetotal = #{datetotal} AND limittotal = #{limittotal} where userID = #{userID}")
    public int updateUserSpend(@Param("userID") String userID, @Param("datetotal") String datetotal, @Param("limittotal") String limittotal);
}
