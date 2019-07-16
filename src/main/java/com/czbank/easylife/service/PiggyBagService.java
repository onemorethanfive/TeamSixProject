package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillCardMapMapper;
import com.czbank.easylife.mapper.PiggyBagMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.PiggyBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.Pipe;
import java.util.List;

@Service
public class PiggyBagService {
    @Autowired
    private PiggyBagMapper piggyBagMapper;


    public PiggyBag getById(String bagId) {
        PiggyBag bills = piggyBagMapper.getById(bagId);
        return bills;
    }

    public PiggyBag getByUser(String userId){
        PiggyBag bill = piggyBagMapper.getByUser(userId);
        return bill;
    }

    public void addBag(PiggyBag piggyBag){
        piggyBagMapper.addBag(piggyBag);
    }

    public void updateBag(PiggyBag piggyBag){
        piggyBagMapper.update(piggyBag);
    }

}
