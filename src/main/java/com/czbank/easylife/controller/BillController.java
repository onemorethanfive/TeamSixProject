package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.service.BillCardMapService;
import com.czbank.easylife.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/bill/")
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public void updateTag(Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "billId", defaultValue = "") final String billId,
                          @RequestParam(value = "billType", defaultValue = "") final String billType,
                          @RequestParam(value = "billNum", defaultValue = "") final String billNum,
                          @RequestParam(value = "billDate", defaultValue = "") final String billDate,
                          @RequestParam(value = "billTag", defaultValue = "") final String billTag,
                          @RequestParam(value = "sign", defaultValue = "") final String sign,
                          @RequestParam(value = "signType", defaultValue = "") final String signType,
                          @RequestParam(value = "billRemarks", defaultValue = "") final String billRemarks
    ) throws Exception {
        billService.addBill(billId, billType, billNum, billDate, billTag, sign, signType, billRemarks);
    }

    @RequestMapping(value = "getBillsByUser", method = RequestMethod.GET)
    public @ResponseBody
    List<Bill> getBillsByUser(Model model,
                              HttpServletRequest request,
                              @RequestParam(value = "userId", defaultValue = "") final String userId
    ) throws Exception {
        return billService.getBillByUserId(userId);
    }
}
