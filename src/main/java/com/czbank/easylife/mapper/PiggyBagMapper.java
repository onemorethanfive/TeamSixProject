package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Card;
import com.czbank.easylife.model.PiggyBag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PiggyBagMapper {
    @Select("SELECT * FROM piggy_bag WHERE bag_id = #{bagId};")
    public PiggyBag getById(@Param("bagId") String bagId);

    @Select("SELECT * FROM piggy_bag WHERE user_id = #{userId};")
    public PiggyBag getByUser(@Param("userId") String userId);

    @Insert("INSERT INTO piggy_bag (money_pool,daily_money,target_money,user_id,start_date,end_date) VALUES (#{moneyPool},#{dailyMoney},#{targetMoney},#{userId},#{startDate},#{endDate});")
    public void addBag(PiggyBag piggyBag);

    @Delete("DELETE FROM piggy_bag WHERE bag_id = #{bagId};")
    public void removeBag(@Param("bagId") String bagId);

    @Update("UPDATE piggy_bag SET (money_pool,daily_money,target_money,user_id,start_date,end_date) VALUES (#{moneyPool},#{dailyMoney},#{targetMoney},#{userId},#{startDate},#{endDate} WHERE bag_id = #{bagId};")
    public void update(PiggyBag piggyBag);

}
