package com.czbank.easylife.controller;

import com.czbank.easylife.service.CardService;
import com.czbank.easylife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/card/")
public class CardController {
    @Autowired
    private CardService cardService;

//    @RequestMapping(value = "login/")
//    @RequestBody
//    public @ResponseBody Object
    @RequestMapping(value = "getMoneyByUser/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Object findUserById(Model model,
                      HttpServletRequest request,
                      @PathVariable String userId) throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("sucess",true);
        try {
            return cardService.getMoneyByUser(userId);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseMessage;
        }

    }
}
