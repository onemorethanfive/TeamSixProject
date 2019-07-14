package com.czbank.easylife.service;

import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.mapper.BillCardMapMapper;
import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.mapper.CardMapper;
import com.czbank.easylife.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BillService {
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private CardMapper cardMapper;

    public void addBill(String billId, String billType, String billNum, String billDate, String billTag, String sign, String signType, String billRemarks) {
        billMapper.addBill(billId, billType, billNum, billDate, billTag, sign, signType, billRemarks);
    }

    public List<Bill> getBillByUserId(String userID) {
        return billMapper.getBillByUserId(userID);
    }

    private BillCardMapMapper billCardMapMapper;


    public Map<String, Double> findBillsByDateUser(String billDate, String userId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssss");
        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        String curday = day.format(new Date());
        List<Bill> bills = billMapper.findBillsByDateUser(billDate, userId);
        Double curMoney = cardMapper.getMoneyByUser(userId);
        Map<String, Double> everydayMoney = new HashMap<>();//<date,money>
        String firstday = billDate;
        try {//循环从第一天到如今，第一天先暂定一年前
            for (int i = 0; i < 365; i++) {
                String lab = curday.substring(0, 8);   //日期取前8位，精确到日
                everydayMoney.put(lab, 0.0);
                Date nextDay = day.parse(curday);
                long l = nextDay.getTime();
                long m = l - 24 * 60 * 60 * 100;
                nextDay = new Date(m);
                curday = nextDay.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Double> everydayUse = everydayMoney;                //everydayUse 每日收支
        for (int i = 0; i < bills.size(); i++) {
            String date = bills.get(i).getBillDate().substring(0, 8);     //对流水的每一项日期精确到日
            String stringType = bills.get(i).getBillType();
            String stringNumber = bills.get(i).getBillNum();
            int type = Integer.parseInt(stringType);
            int number = Integer.parseInt(stringNumber);                       //type和number转为int，type=0收入，1支出
            everydayUse.put(date, (everydayUse.get(date) + Math.pow((-1), type * number)));    //按日统计，每条流水更新一次
        }
        for (String key : everydayUse.keySet()) {
            double today = everydayUse.get(key);
            for (String key2 : everydayUse.keySet()) {
                if (Integer.parseInt(key) < Integer.parseInt(key2)) {
                    today = today - everydayUse.get(key2);
                }
            }
            everydayMoney.put(key, today);
        }
        return everydayMoney;
    }

}
