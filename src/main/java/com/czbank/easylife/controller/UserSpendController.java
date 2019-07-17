package com.czbank.easylife.controller;

import com.czbank.easylife.model.UserSpend;
import com.czbank.easylife.service.UserSpendService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.*;

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
                                                                @RequestParam(value = "userId", defaultValue = "") final String userId
    )throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today=sdf.format(new Date(System.currentTimeMillis()));
        System.out.println(today);
        try {
            return userSpendService.getLimitReminder(userId,today);
        }
        catch (Exception e){
            e.printStackTrace();
            List<Double> a =new ArrayList<>();
            return a;
        }
    }

    @RequestMapping(value = "updateUserSpend", method = RequestMethod.GET)
    public @ResponseBody
    int updateTag(Model model,
                  HttpServletRequest request,
                  @RequestParam(value = "userID", defaultValue = "") final String userID,
                  @RequestParam(value = "totaldate", defaultValue = "") final String totaldate,
                  @RequestParam(value = "totallimit", defaultValue = "") final String totallimit
    ) throws Exception {
        return userSpendService.updateUserSpend(userID,totaldate,totallimit);
    }


}
