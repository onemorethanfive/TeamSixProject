package com.czbank.easylife.controller;

import com.czbank.easylife.model.Card;
import com.czbank.easylife.model.User;
import com.czbank.easylife.service.CardService;
import com.czbank.easylife.util.JsonHelper;
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
        responseMessage.put("success",true);
        try {
            return cardService.getMoneyByUser(userId);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseMessage;
        }
    }

    @RequestMapping(value = "addCard",method = RequestMethod.POST)
    public @ResponseBody
    Object addCard(@RequestBody String card) throws Exception {
        String responseBody = "";
        Card curcard = JsonHelper.getInstance().read(card,Card.class);
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            Card upCard=cardService.addCard(curcard);
            if(upCard!=null){
                return upCard;
            }else{
                responseMessage.put("success",false);
                return responseMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage;
        }
    }


    @RequestMapping(value = "getCardByUser/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Object getCardByUser(Model model,
                        HttpServletRequest request,
                        @PathVariable String userId) throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            return cardService.getCardByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage;
        }
    }
}
