package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class billService {
    @Autowired
    private BillMapper billMapper;

    public Bill getBillById(String billId){
        Bill bill;
        bill = billMapper.getBillById(billId);
        return bill;
    }

    public void addBill(Bill newBill){
        billMapper.addBill(newBill.getBillId(),newBill.getBillType(),newBill.getBillNum(),
                newBill.getBillDate(),newBill.getBillTag(),newBill.getSign(),newBill.getSignType(),
                newBill.getBillRemarks());
    }

}
