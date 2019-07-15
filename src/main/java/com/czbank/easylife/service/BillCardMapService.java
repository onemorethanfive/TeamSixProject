package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillCardMapMapper;
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

    public int bindBillCard(String billId, String cardId ){
        return billCardMapMapper.addBillIntoMap(billId,cardId,"");
    }

    public void updateUserTag(String billId, String tag){
        String tagStr = "";
        switch(tag){
            case "饮食":
                tagStr="EAT";
                break;
            case "衣物":
                tagStr="CLOTHES";
                break;
            case "住宿":
                tagStr="HOUSE";
                break;
            case "旅行":
                tagStr="TRAVEL";
                break;
            case "娱乐":
                tagStr="AMUSEMENT";
                break;
            default:
                tagStr="OTHER";
        }
        billCardMapMapper.updateTag(billId,tagStr);
    }

}
