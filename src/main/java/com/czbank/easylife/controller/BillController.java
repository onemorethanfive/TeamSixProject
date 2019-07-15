package com.czbank.easylife.controller;

import com.czbank.easylife.model.Bill;
import com.czbank.easylife.service.BillCardMapService;
import com.czbank.easylife.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
