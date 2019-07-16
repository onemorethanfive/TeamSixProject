package com.czbank.easylife.service;

import com.czbank.easylife.mapper.DateBalanceMapMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.DateBalanceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DateBalanceMapService {
    @Autowired
    private DateBalanceMapMapper dateBalanceMapMapper;

    public List<DateBalanceMap> getBalanceByUser(String user_id){

        SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
        String curday = day.format(new Date());
        int lastYearInt = Integer.parseInt(curday)-10000;

        List<DateBalanceMap> bs = dateBalanceMapMapper.getBalanceByUser(user_id,lastYearInt);
        return bs;
    }

    public List<Integer> findBestInvestment(String user_id){
        List<DateBalanceMap> a = dateBalanceMapMapper.getAllBalanceOnlyByUser(user_id);
        List minAndVar= new ArrayList();
        List<Double> t =new ArrayList();
        List<Integer> investmentType = new ArrayList<>();
        for (int i =0;i<a.size();i++){
            String x=a.get(i).getBalance();
            t.add(Double.parseDouble(x));
        }
        double min = Collections.min(t);
        minAndVar.add(Collections.min(t));

        int num = t.size();
        double sum = 0;
        for (int i = 0; i < num; i++) {
            sum = sum + t.get(i);
        }
        double average = sum/num;


        double sum2 = 0;
        for (int i = 0; i < num; i++) {
            sum2 += Math.sqrt(( t.get(i) - average) * (t.get(i) - average));
        }
        double var= sum2 / (num - 1);

        minAndVar.add(var);

        if (min<10000){
            investmentType.add(0);
        }
        else if (min<1000000){
            investmentType.add(1);
        }
        else {
            investmentType.add(2);
        }

        if (var<10000){
            investmentType.add(0);
        }
        else if (var<1000000){
            investmentType.add(1);
        }
        else {
            investmentType.add(2);
        }

        return investmentType;
    }
}
