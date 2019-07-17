package com.czbank.easylife.controller;

import com.czbank.easylife.model.PiggyBag;
import com.czbank.easylife.service.PiggyBagService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/piggy/")
@CrossOrigin(origins="http://localhost:8080")
public class PiggyBagController {
    @Autowired
    private PiggyBagService piggyBagService;

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public Object getBagById(Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "bagId", defaultValue = "") final String bagId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            return piggyBagService.getById(bagId);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "getByUser", method = RequestMethod.GET)
    public Object getByUser(Model model,
                             HttpServletRequest request,
                             @RequestParam(value = "userId", defaultValue = "") final String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            return piggyBagService.getByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "addBag", method = RequestMethod.GET)
    public @ResponseBody
    Object addBag(Model model,
                   HttpServletRequest request,
                  @RequestParam(value = "dailyMoney", defaultValue = "") final String dailyMoney,
                  @RequestParam(value = "startDate", defaultValue = "") final String startDate,
                  @RequestParam(value = "endDate", defaultValue = "") final String endDate,
                  @RequestParam(value = "targetMoney", defaultValue = "") final String targetMoney,
                  @RequestParam(value = "moneyPool", defaultValue = "") final String moneyPool,
                  @RequestParam(value = "userId", defaultValue = "") final String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        PiggyBag piggyBag = new PiggyBag();
        piggyBag.setDailyMoney(dailyMoney);
        piggyBag.setEndDate(endDate);
        piggyBag.setStartDate(startDate);
        piggyBag.setMoneyPool(moneyPool);
        piggyBag.setTargetMoney(targetMoney);
        piggyBag.setUserId(userId);
        try {
            piggyBagService.addBag(piggyBag);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "updateBag", method = RequestMethod.POST)
    public @ResponseBody
    Object updateBag(Model model,
                   HttpServletRequest request,
                   @RequestBody String bag

    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        PiggyBag piggyBag = JsonHelper.getInstance().read(bag,PiggyBag.class);
        try {
            piggyBagService.updateBag(piggyBag);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "deleteBag", method = RequestMethod.POST)
    public @ResponseBody
    Object deleteBag(Model model,
                      HttpServletRequest request,
                      @RequestBody String bag

    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        PiggyBag piggyBag = JsonHelper.getInstance().read(bag,PiggyBag.class);
        try {
            piggyBagService.updateBag(piggyBag);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }


}
