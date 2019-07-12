package com.czbank.easylife.controller;

import com.czbank.easylife.model.User;
import com.czbank.easylife.service.UserService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "getUser/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Object findUserById(Model model,
                      HttpServletRequest request,
                      @PathVariable String userId) throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            return userService.getUserById(userId);
        }
        catch (Exception e){
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public @ResponseBody
    Object signIn(Model model,
                   HttpServletRequest request,
                   @RequestParam(value = "userId", defaultValue = "") final String userId,
                   @RequestParam(value = "userPsw", defaultValue = "") final String userPsw
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = new User();
        user.setUserId(userId);
        user.setUserPsw(userPsw);
        try {
            if (userService.login(userId,userPsw)){
                responseMessage.put("message","登录成功");
                return userService.getUserById(userId);
            }
            else {
                responseMessage.put("success",false);
                responseMessage.put("reason","登录失败:密码错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

//    @RequestMapping(value = "signUp", method = RequestMethod.POST)
//    public @ResponseBody
//    Object signUp(Model model,
//                  HttpServletRequest request,
//                  @RequestParam(value = "userId", defaultValue = "") final String userId,
//                  @RequestParam(value = "userPsw", defaultValue = "") final String userPsw,
//                  @RequestParam(value = "userName", defaultValue = "") final String userName
//    ) throws Exception {
//        String responseBody = "";
//        Map responseMessage = new HashMap();
//        responseMessage.put("success", true);
//        User user = new User();
//        user.setUserId(userId);
//        user.setUserPsw(userPsw);
//        user.setUserName(userName);
//        try {
//            User check = userService.getUserById(userId);
//            if(check != null){
//                responseMessage.put("message","注册失败：账户已被注册");
//                responseMessage.put("success", false);
//                return responseMessage;
//            }
//            userService.addUser(user);
//            responseMessage.put("message","注册成功");
//            return responseMessage;
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseMessage.put("reason", "系统错误"+e.getMessage());
//            responseMessage.put("success", false);
//        }
//        return responseMessage;
//    }
@RequestMapping(value = "signUp", method = RequestMethod.POST)
public @ResponseBody
Object signUp(@RequestBody String body
//              @RequestParam(value = "userId", defaultValue = "") final String userId,
//              @RequestParam(value = "userPsw", defaultValue = "") final String userPsw,
//              @RequestParam(value = "userName", defaultValue = "") final String userName
) throws Exception {
    String responseBody = "";
    Map responseMessage = new HashMap();
    responseMessage.put("success", true);
    User user = JsonHelper.getInstance().read(body,User.class);
//    user.setUserId(userId);
//    user.setUserPsw(userPsw);
//    user.setUserName(userName);
    try {
        User check = userService.getUserById(user.getUserId());
        if(check != null){
            responseMessage.put("message","注册失败：账户已被注册");
            responseMessage.put("success", false);
            return responseMessage;
        }
        userService.addUser(user);
        responseMessage.put("message","注册成功");
        return responseMessage;
    } catch (Exception e) {
        e.printStackTrace();
        responseMessage.put("reason", "系统错误"+e.getMessage());
        responseMessage.put("success", false);
    }
    return responseMessage;
}

    @RequestMapping(value = "modifyInfo", method = RequestMethod.POST)
    public @ResponseBody
    Object modifyInfo(Model model,
                  HttpServletRequest request,
                  //@RequestParam(value = "idNum", defaultValue = "") final String idNum,
                  @RequestParam(value = "userGender", defaultValue = "") final String userGender,
                  @RequestParam(value = "userLoc", defaultValue = "") final String userLoc,
                      @RequestParam(value = "userId") final String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = new User();
        user.setUserId(userId);
        //user.setIdNum(idNum);
        user.setUserGender(userGender);
        user.setUserLoc(userLoc);
        try {
            userService.modifyInfo(user);
            responseMessage.put("message","资料修改成功");
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "modifyPsw", method = RequestMethod.POST)
    public @ResponseBody
    Object modifyPsw(Model model,
                      HttpServletRequest request,
                     @RequestParam(value = "userId", defaultValue = "") final String userId,
                     @RequestParam(value = "userPswOld", defaultValue = "") final String userPswOld,
                     @RequestParam(value = "userPswNew", defaultValue = "") final String userPswNew
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = userService.getUserById(userId);
        if (user.getUserPsw().equals(userPswOld)){
            /*

        修改密码需要的验证步骤

         */
            user.setUserPsw(userPswNew);
        }
        else {
            responseMessage.put("message","密码修改失败：原密码错误");
            responseMessage.put("success", false);
            return responseMessage;
        }

        try {
            userService.modifyInfo(user);
            responseMessage.put("message","密码修改成功");
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }
}
