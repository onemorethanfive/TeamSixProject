package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.DateBalanceMap;
import com.czbank.easylife.service.BillCardMapService;
import com.czbank.easylife.service.DateBalanceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dateBalance/")
@CrossOrigin
public class DateBalanceMapController {
    @Autowired
    private DateBalanceMapService dateBalanceMapService;

    @RequestMapping(value = "getBalanceByUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object getBalanceByUser(Model model,
                           HttpServletRequest request, @PathVariable String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            List<DateBalanceMap> bs = dateBalanceMapService.getBalanceByUser(userId);
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }


    @RequestMapping(value = "findBestInvestment/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object findBestInvestment(Model model,
                            HttpServletRequest request, @PathVariable String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            List<Integer> bestInvestment = dateBalanceMapService.findBestInvestment(userId);
            return bestInvestment;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }
}
