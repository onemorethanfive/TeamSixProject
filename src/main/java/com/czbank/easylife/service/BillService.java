package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillMapper billMapper;

    public void addBill(String billId, String billType, String billNum, String billDate, String billTag, String sign, String signType, String billRemarks) {
        billMapper.addBill(billId, billType, billNum, billDate,billTag, sign, signType, billRemarks);
    }

    public List<Bill> getBillByUserId(String userID) {
        return billMapper.getBillByUserId(userID);
    }
}
