package com.czbank.easylife.mapper;

import com.czbank.easylife.model.Household;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HouseholdMapper {
//
    @Select("SELECT * FROM household WHERE user_id = #{userId};")
    public List<Household> findHouseholdByUserId(@Param("userId") String userId);

    @Select("SELECT * FROM household WHERE household_id = #{householdId} AND user_id = #{userId};")
    public Household findHouseholdByHouseholdId(@Param("householdId")String householdId,@Param("userId") String userId);

    @Insert("INSERT INTO household (user_id,household_id,household_name,household_num,household_loc) VALUES (#{userId},#{householdId},#{householdName},#{householdNum},#{householdLoc});")
    public void addHousehold(@Param("userId")String userId, @Param("householdId")String householdId, @Param("householdName")String householdName, @Param("householdNum")String householdNum, @Param("householdLoc")String householdLoc);

    @Update("UPDATE household SET household_name = #{householdName}, household_num = #{householdNum}, household_loc = #{householdLoc} WHERE household_id = #{householdId} AND user_id = #{userId};;")
    public void updateHousehold(@Param("householdName")String householdName, @Param("householdNum")String householdNum, @Param("householdLoc")String householdLoc,@Param("householdId")String householdId,@Param("userId") String userId);
    //@Delete("DELETE FROM card_user_map WHERE card_id = #{cardId};")
    //    public void removeCardFromMap(@Param("cardId") String cardId);
    @Delete("DELETE from household WHERE household_id = #{householdId} AND user_id = #{userId} ;")
    public void removeHousehold(@Param("householdId")String householdId,@Param("userId") String userId);
}
