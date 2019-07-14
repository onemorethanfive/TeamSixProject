package com.czbank.easylife.mapper;

import com.czbank.easylife.model.DateBalanceMap;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface DateBalanceMapMapper {
    @Select("SELECT * FROM date_balance_map WHERE user_id = #{userId} AND CAST(DATE_FORMAT(date,'%Y%m%d') AS UNSIGNED INTEGER) > #{date};")
    public List<DateBalanceMap> getBalanceByUser(@Param("userId") String userId,@Param("date") int date);

}
