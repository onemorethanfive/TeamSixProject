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
        userMapper.addUser(newUser.getUserId(),newUser.getUserName(),newUser.getUserPsw(),
                newUser.getIdNum(),newUser.getUserGender(),
                newUser.getUserLoc());
    }

    public void modifyInfo(User modifyUser){
        User user = userMapper.findUserById(modifyUser.getUserId());
        if(modifyUser.getUserLoc().equals(""))
            modifyUser.setUserLoc(user.getUserLoc());
        if(modifyUser.getUserPsw().equals(""))
            modifyUser.setUserPsw(user.getUserPsw());
        if(modifyUser.getUserGender().equals(""))
            modifyUser.setUserGender(user.getUserGender());
        userMapper.updateUser(modifyUser.getUserPsw(),modifyUser.getUserGender(),modifyUser.getUserLoc(),modifyUser.getUserId());

    }
}
