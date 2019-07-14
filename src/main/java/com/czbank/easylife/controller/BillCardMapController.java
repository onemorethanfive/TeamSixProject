package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.service.BillCardMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/billCardMap/")
@CrossOrigin
public class BillCardMapController {
    @Autowired
    private BillCardMapService billCardMapService;

    @RequestMapping(value = "findBillsByCard/{cardId}", method = RequestMethod.GET)
    public @ResponseBody
    Object findBillsByCard(Model model,
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

    @RequestMapping(value = "updateTag", method = RequestMethod.GET)
    public @ResponseBody
    void updateTag(Model model,
                           HttpServletRequest request,
                   @RequestParam(value = "billId", defaultValue = "") final String billId,
                   @RequestParam(value = "tag", defaultValue = "") final String tag
    ) throws Exception {
        billCardMapService.updateUserTag(billId,tag);
    }

    @RequestMapping(value = "addBillCard", method = RequestMethod.GET)
    public @ResponseBody
    void addBillCard(Model model,
                   HttpServletRequest request,
                   @RequestParam(value = "billId", defaultValue = "") final String billId,
                   @RequestParam(value = "cardId", defaultValue = "") final String cardId
    ) throws Exception {
        billCardMapService.bindBillCard(billId,cardId);
    }

}
