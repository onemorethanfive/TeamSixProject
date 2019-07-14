package com.czbank.easylife.controller;

import com.czbank.easylife.model.Card;
import com.czbank.easylife.model.Demo;
import com.czbank.easylife.service.CardUserMapService;
import com.czbank.easylife.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cardUserMap/")
@CrossOrigin
public class CardUserMapController {
    @Autowired
    private CardUserMapService cardUserMapService;

    @RequestMapping(value = "findCardsByUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object findCardsByUser(Model model,
                           HttpServletRequest request, @PathVariable String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            List<Card> cards = cardUserMapService.findCardsByUserId(userId);
            return cards;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody
    Object addCard(Model model,
                      HttpServletRequest request,
                   @RequestParam(value = "userId", defaultValue = "") final String userId,
                   @RequestParam(value = "cardId", defaultValue = "") final String cardId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            cardUserMapService.addCard(cardId,userId);
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

}
