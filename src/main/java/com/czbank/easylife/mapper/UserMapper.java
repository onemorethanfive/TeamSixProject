package com.czbank.easylife.mapper;

import com.czbank.easylife.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE user_id = #{userId};")
    public User findUserById(@Param("userId") String userId);

    @Insert("INSERT INTO `user` (`user_id`, `user_name`, `user_psw`, `ID_num`, `user_gender`, `user_loc`) VALUES (#{userId},#{user_psw},#{ID_num},#{user_gender},#{user_loc});")
    public void addUser(@Param("userId")String userId,@Param("userName")String userName,@Param("userPsw")String userPsw,@Param("IdNum")String IdNum,@Param("userGender")String userGender,@Param("userLoc")String userLoc,@Param("userBudget")String userBudget);

    @Update("Update user SET user_psw = #{userPsw},user_gender = #{userGender},user_loc = #{userLoc} WHERE user_id = #{userId};")
    public void updateUser(@Param("userPsw")String userPsw,@Param("userGender")String userGender,@Param("userLoc")String userLoc);
}
