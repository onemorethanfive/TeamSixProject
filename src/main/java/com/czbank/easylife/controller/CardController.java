package com.czbank.easylife.controller;

import com.czbank.easylife.model.Card;
import com.czbank.easylife.model.User;
import com.czbank.easylife.service.BillService;
import com.czbank.easylife.service.CardService;
import com.czbank.easylife.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/card/")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private BillService billService;

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

    @RequestMapping(value = "getDifference/{userId}",method = RequestMethod.GET)
    public @ResponseBody
    Map<Date,Integer> getDifference(Model model,
                         HttpServletRequest request,
                         @PathVariable String userId) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long cur = System.currentTimeMillis();
        String dateStr = format.format(cur);
        Date date = format.parse(dateStr);
        Map<Date, Integer> diff = new HashMap<>();
        int[] diffs = {3874, 5232, 1234, 4531, 34452, 12331, 33451, 45312, 21312, 5332,
                2311, 1231, 1231, 4443, 1231, 2134, 12312, 12312, 23443, 12334,
                1231, 121, 3454, 3234, 23434, 54532, 35542, 23234, 12334, 12312
        };
        for (int i = 0; i < 30; i++) {
            diff.put(date, diffs[i]);
            cur -= 24 * 60 * 60 * 1000;
            dateStr = format.format(cur);
            date = format.parse(dateStr);
        }
        return diff;
    }


    @RequestMapping(value = "findBillsByMonth", method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Double> findBillsByMonth(Model model,
                            HttpServletRequest request,
                            @RequestParam(value = "billDate", defaultValue = "") final String billDate,
                            @RequestParam(value = "cardId", defaultValue = "") final String cardId
    ) throws Exception {

        String responseBody = "";
        Map responseMessage = new HashMap();
        try {
            return billService.findBillsByDateUser(billDate,cardId);
        }
        catch (Exception e){
            e.printStackTrace();
            return responseMessage;
        }
    }
}
