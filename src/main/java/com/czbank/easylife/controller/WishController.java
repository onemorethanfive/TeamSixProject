package com.czbank.easylife.controller;

import com.alibaba.fastjson.JSON;
import com.czbank.easylife.model.Wish;
import com.czbank.easylife.service.UserService;
import com.czbank.easylife.service.WishService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wish/")
@CrossOrigin
public class WishController {
    @Autowired
    private WishService wishService;
    @Autowired
    private UserService userService;

    /*
        {
            "userId":"fff",
            "householdId":"3",
            "householdName":"er",
            "householdNum":"",
            "householdLoc":""
        }
        提供所有字段,没写的填""(尽量都写)
     */


    @RequestMapping(value = "getWishByUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object getWishByUser(@PathVariable String userId) throws Exception{
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            return wishService.getWishes("userId");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }


    @RequestMapping(value = "addWish", method = RequestMethod.POST)
    public @ResponseBody
    Object addWish(@RequestBody String body) throws Exception{
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        Wish wish = JsonHelper.getInstance().read(body,Wish.class);
        if(userService.getUserById(wish.getUserId()) == null){
            responseMessage.put("reason", "请登录");
            responseMessage.put("success", false);
            return responseMessage;
        }
        try { wishService.addWish(wish);

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
            "userId":"fff",
            "householdId":"3"
        }
     */
    @RequestMapping(value = "deleteWish", method = RequestMethod.POST)
    public @ResponseBody
    Object deleteWish(@RequestBody String body) throws Exception{
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        //Household household = JsonHelper.getInstance().read(body,Household.class);
        Map map = JSON.parseObject(body,Map.class);
        Wish wish = wishService.getWish(map.get("wishId").toString());
        try {
            if(wish != null){
                wishService.removeWish(wish);
                responseMessage.put("message","愿望表资料删除成功");
                return responseMessage;
            }
            else {
                responseMessage.put("reason", "数据库错误:没有该条目");
                responseMessage.put("success", false);
                return responseMessage;
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
            "householdId":"3",
            "householdName":"er",
            "householdNum":"",
            "householdLoc":""
        }
        不需要修改的条目置为""
     */

    @RequestMapping(value = "modifyInfo", method = RequestMethod.POST)
        public @ResponseBody
        Object modifyInfo(@RequestBody String body)throws Exception {
            String responseBody = "";
            Map responseMessage = new HashMap();
            responseMessage.put("success", true);
           Wish wish= JsonHelper.getInstance().read(body,Wish.class);
            try {
                if(wishService.getWish(wish.getWishId()) != null){
                    wishService.modifyInfo(wish);
                    responseMessage.put("message","愿望表资料修改成功");
                    return responseMessage;
                }
                else {
                    responseMessage.put("reason", "数据库错误:无该愿望表");
                    responseMessage.put("success", false);
                    return responseMessage;
                }

            } catch (Exception e) {
                e.printStackTrace();
                responseMessage.put("reason", "系统错误"+e.getMessage());
                responseMessage.put("success", false);
            }
            return responseMessage;
        }
}
