package com.czbank.easylife.mapper;


import com.czbank.easylife.model.Wish;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WishMapper {

    @Select("SELECT * FROM wish WHERE user_id = #{userId};")
    public List<Wish> findWishByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM wish WHERE wish_id = #{wishId};")
    public Wish findWishByWishId(@Param("wishId") String wishId);

    @Insert("INSERT INTO wish(wish_id,user_id,wish_name,wish_num,star_date,target_date,wish_state,current_state,wish_remark," +
            "interest_rate,interest_id,wish_bill_id) VALUES (#{wishId},#{userId},#{wishName},#{wishNum},#{starDate},#{targetDate}" +
            ",#{wishState},#{currentState},#{wishRemark},#{interestRate},#{interestId},#{wishBillId});")
    public void addWish(@Param("wishId")String wishId, @Param("userId")String userId, @Param("wishName")String wishName, @Param("wishNum")String wishNum,
                             @Param("starDate")String starDate,@Param("targetDate")String targetDate,@Param("wishState")String wishState,@Param("currentState")String currentState,
                             @Param("wishRemark")String wishRemark,@Param("interestRate")String interestRate,@Param("interestId")String interestId,@Param("wishBillId")String wishBillId);

    @Update("UPDATE wish SET wish_id = #{wishId},wish_id = #{wishId},user_id = #{userId},wish_name = #{wishName},wishNum = #{wish_num},starDate = #{star_date},targetDate = #{target_date},wishState = #{wish_state},currentState = #{current_state}" +
            "wish_remark = #{wishRemark},interest_rate = #{interestRate},interest_id = #{interestId},wish_billId = #{wishBillId}")
    public void updateWish(@Param("wishId")String wishId, @Param("userId")String userId, @Param("wishName")String wishName, @Param("wishNum")String wishNum,
                             @Param("starDate")String starDate,@Param("targetDate")String targetDate,@Param("wishState")String wishState,@Param("currentState")String currentState,
                             @Param("wishRemark")String wishRemark,@Param("interestRate")String interestRate,@Param("interestId")String interestId,@Param("wishBillId")String wishBillId);

    @Delete("DELETE from wish WHERE wish_id = #{wishId} } ;")
    public void removeWish(@Param("wishId")String wishId);

}
