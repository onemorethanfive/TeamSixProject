package com.czbank.easylife.controller;

import com.alibaba.fastjson.JSON;
import com.czbank.easylife.model.Household;
import com.czbank.easylife.service.HouseholdService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/household/")
public class HouseholdController {
    @Autowired
    private HouseholdService householdService;

    /*
        {
            "userId":"fff",
            "householdId":"3",
            "householdName":"er",
            "householdNum":"",
            "householdLoc":""
        }
        提供所有字段,没写的填""
     */
    @RequestMapping(value = "addHousehold", method = RequestMethod.POST)
    public @ResponseBody
    Object addHousehold(@RequestBody String body) throws Exception{
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        Household household = JsonHelper.getInstance().read(body,Household.class);
        try {
                    householdService.addHousehold(household);
                    responseMessage.put("message","户组资料添加成功");
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
    @RequestMapping(value = "deleteHousehold", method = RequestMethod.POST)
    public @ResponseBody
    Object deleteHousehold(@RequestBody String body) throws Exception{
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        //Household household = JsonHelper.getInstance().read(body,Household.class);
        Map map = JSON.parseObject(body,Map.class);
        Household household = householdService.getHousehold(map.get("userId").toString(),map.get("householdId").toString());
        try {
            householdService.deletdHousehold(household);
            responseMessage.put("message","户组资料删除成功");
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
            Household household = JsonHelper.getInstance().read(body,Household.class);
            try {
                householdService.modifyInfo(household);
                responseMessage.put("message","户组资料修改成功");
                return responseMessage;
            } catch (Exception e) {
                e.printStackTrace();
                responseMessage.put("reason", "系统错误"+e.getMessage());
                responseMessage.put("success", false);
            }
            return responseMessage;
        }
}
