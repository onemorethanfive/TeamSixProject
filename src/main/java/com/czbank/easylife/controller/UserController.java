package com.czbank.easylife.controller;

import com.czbank.easylife.model.User;
import com.czbank.easylife.service.UserService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@CrossOrigin
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

    /*
        {
            "userId":"fff",
            "userPsw":"12345"
        }
        返回User Object
     */
    @RequestMapping(value = "signIn", method = RequestMethod.POST)
    public @ResponseBody
    Object signIn(@RequestBody String body)throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = JsonHelper.getInstance().read(body,User.class);

        try {
            if (userService.login(user.getUserId(),user.getUserPsw())){
                responseMessage.put("message","登录成功");
                return userService.getUserById(user.getUserId());
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

    /*
        {
            "userId":"fff",
            "userPsw":"12345",
            "userName":"sb"
        }
        添加成功后其他条目给予默认值""
     */
    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public @ResponseBody
    Object signUp(@RequestBody String body) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = JsonHelper.getInstance().read(body,User.class);

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

    /*
        {
            "userId":"qqq",
            "userLoc":"c",
            "userGender":"0"
        }
        可修改:userLoc,userGender,userBudget,只写要改的参数
     */
    @RequestMapping(value = "modifyInfo", method = RequestMethod.POST)
    public @ResponseBody
    Object modifyInfo(@RequestBody String body)throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        User user = JsonHelper.getInstance().read(body,User.class);
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

    /*
        {
            "userId":"qqq",
            "userPswOld":"12345",
            "userPswNew":"22"
        }
     */

    @RequestMapping(value = "modifyPsw", method = RequestMethod.POST)
    public @ResponseBody
    Object modifyPsw(@RequestBody String body) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);

        Map map = JSON.parseObject(body,Map.class);
        User user = userService.getUserById(map.get("userId").toString());
        if (user.getUserPsw().equals(map.get("userPswOld").toString())){
            /*

        修改密码需要的验证步骤

         */
            user.setUserPsw(map.get("userPswNew").toString());
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
