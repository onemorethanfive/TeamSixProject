package com.czbank.easylife.service;

import com.czbank.easylife.mapper.UserMapper;
import com.czbank.easylife.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(String userId){
        return userMapper.findUserById(userId);
    }

    public boolean login(String userId, String userPsw){
        User user = userMapper.findUserById(userId);
        return user.getUserPsw().equals(userPsw);
    }

    public void addUser(User newUser){
        userMapper.addUser(newUser.getUserId(),newUser.getUserName(),newUser.getUserPsw());
    }

    public void modifyInfo(User modifyUser){
        User user = userMapper.findUserById(modifyUser.getUserId());
        //modifyUser传入的是只有需修改部分的字段,不修改的为null
        if(modifyUser.getUserLoc() == null)
            modifyUser.setUserLoc(user.getUserLoc());
        if(modifyUser.getUserPsw() == null)
            modifyUser.setUserPsw(user.getUserPsw());
        if(modifyUser.getUserGender() == null)
            modifyUser.setUserGender(user.getUserGender());
        if(modifyUser.getUserBudget() == null)
            modifyUser.setUserBudget(user.getUserBudget());
        userMapper.updateUser(modifyUser.getUserPsw(),modifyUser.getUserGender(),modifyUser.getUserLoc(),modifyUser.getUserId(),modifyUser.getUserBudget());

    }
}
