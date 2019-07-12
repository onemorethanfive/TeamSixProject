package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.service.BillCardMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cardUserMap/")
public class BillCardMapController {
    @Autowired
    private BillCardMapService billCardMapService;

    @RequestMapping(value = "findCardsByUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object findCardsByUser(Model model,
                           HttpServletRequest request, @PathVariable String cardId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            List<Bill> bills = billCardMapService.findBillsByCard(cardId);
            return bills;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

}
