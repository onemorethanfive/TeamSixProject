package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.BillTagMap;
import com.czbank.easylife.model.Household;
import com.czbank.easylife.model.PiggyBag;
import com.czbank.easylife.service.BillCardMapService;
import com.czbank.easylife.service.BillService;
import com.czbank.easylife.service.PiggyBagService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/piggy/")
@CrossOrigin
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

    @RequestMapping(value = "addBag", method = RequestMethod.POST)
    public @ResponseBody
    Object addBag(Model model,
                   HttpServletRequest request,
                   @RequestBody String bag

    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        PiggyBag piggyBag = JsonHelper.getInstance().read(bag,PiggyBag.class);
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
