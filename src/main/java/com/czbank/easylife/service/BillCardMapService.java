package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillCardMapMapper;
import com.czbank.easylife.mapper.CardMapper;
import com.czbank.easylife.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillCardMapService {
    @Autowired
    private BillCardMapMapper billCardMapMapper;

    public List<Bill> findBillsByCard(String billId) {
        List<Bill> bills = billCardMapMapper.findBillsByCard(billId);
        return bills;
    }

}
