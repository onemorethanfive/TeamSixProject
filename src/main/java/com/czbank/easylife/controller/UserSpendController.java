package com.czbank.easylife.controller;

import com.czbank.easylife.model.UserSpend;
import com.czbank.easylife.service.UserSpendService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userSpend/")
@CrossOrigin

public class UserSpendController {
    @Autowired
    private UserSpendService userSpendService;

    @RequestMapping(value = "userSpend", method = RequestMethod.GET)
    public @ResponseBody
    List<Double> userSpend(Model model,
                                                                HttpServletRequest request,
                                                                @RequestParam(value = "userId", defaultValue = "") final String userId,
                                                                @RequestParam(value = "today", defaultValue = "") final String today
    )throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        try {
            return userSpendService.getLimitReminder(userId,today);
        }
        catch (Exception e){
            e.printStackTrace();
            List<Double> a =new ArrayList<>();
            return a;
        }
    }


}
