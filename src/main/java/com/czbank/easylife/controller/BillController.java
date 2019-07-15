package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.BillTagMap;
import com.czbank.easylife.model.DateBalanceMap;
import com.czbank.easylife.service.BillCardMapService;
import com.czbank.easylife.service.BillService;
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
@RequestMapping("/bill/")
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillCardMapService billCardMapService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public int updateTag(Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "billNum", defaultValue = "") final String billNum,
                          @RequestParam(value = "billRemarks", defaultValue = "") final String billRemarks,
                          @RequestParam(value = "cardId", defaultValue = "") final String cardId
    ) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssss");
        String billDate = sf.format(new Date(System.currentTimeMillis()));
        String billId = billService.lifePay(billNum, billDate, billRemarks);
        return billCardMapService.bindBillCard(billId,cardId);
    }

    @RequestMapping(value = "getBillsByUser", method = RequestMethod.GET)
    public @ResponseBody
    List<Bill> getBillsByUser(Model model,
                              HttpServletRequest request,
                              @RequestParam(value = "userId", defaultValue = "") final String userId
    ) throws Exception {
        return billService.getBillByUserId(userId);
    }

    @RequestMapping(value = "getBillsByMonth/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    Object getBillsByMonth(Model model,
                            HttpServletRequest request, @PathVariable String userId
    ) throws Exception {
        String responseBody = "";
        Map responseMessage = new HashMap();
        responseMessage.put("success", true);
        try {
            List<BillTagMap> bs = billService.getBillByMonth(userId);
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.put("reason", "系统错误"+e.getMessage());
            responseMessage.put("success", false);
        }
        return responseMessage;
    }

    @RequestMapping(value = "getBillsByUserAndTag", method = RequestMethod.GET)
    public @ResponseBody
    List<Bill> getBillsByUserAndTag(Model model,
                              HttpServletRequest request,
                              @RequestParam(value = "userId", defaultValue = "") final String userId,
                              @RequestParam(value = "tag", defaultValue = "") final String tag
    ) throws Exception {
        return billService.getBillByUserIdAndTag(userId,tag);
    }
}
