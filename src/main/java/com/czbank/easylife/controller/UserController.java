package com.czbank.easylife.controller;

import com.czbank.easylife.model.User;
import com.czbank.easylife.service.UserService;
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

//    @RequestMapping(value = "login/")
//    @RequestBody
//    public @ResponseBody Object
    @RequestMapping(value = "getUser/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Object findUserById(Model model,
                      HttpServletRequest request,
                      @PathVariable String userId) throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("sucess",true);
        try {
            return userService.getUserById(userId);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseMessage;
        }

    }
}
