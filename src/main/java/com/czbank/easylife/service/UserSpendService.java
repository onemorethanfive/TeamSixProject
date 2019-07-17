package com.czbank.easylife.service;
import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.mapper.UserSpendMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.DateBalanceMap;
import com.czbank.easylife.model.UserSpend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.SplittableRandom;
@Service
public class UserSpendService {
    @Autowired
    private UserSpendMapper userSpendMapper;
    @Autowired
    private BillMapper billMapper;
//list:第一项今日剩余限额，第二项总剩余余额
    //输入：userid，今天日期，限额到今天天数，限额总天数，限额金额
    public List<Double> getLimitReminder(String userId, String today ) throws ParseException {
        List<Bill> todaybill  = billMapper.findBillsByDateUser(today,userId);
        UserSpend restTillYesterday = userSpendMapper.findTotalspendByIdAndDate(userId,today);
        Double totalday = Double.valueOf(restTillYesterday.getDatetotal());
        Double totallimit = Double.valueOf(restTillYesterday.getLimittotal());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date a = sdf.parse(restTillYesterday.getDate());
        Date b = sdf.parse(restTillYesterday.getStart());
        double pastday = a.getTime()-b.getTime();
        List<Double> answer = new ArrayList<>();
        double totalamoounttoday = (totallimit-Double.valueOf(restTillYesterday.getTotalspend()))/(totalday-pastday);
        List<Double> billintoday = new ArrayList<>();
        double sum = 0;
        for (int i=0;i<todaybill.size();i++){
            double t=Double.valueOf(todaybill.get(i).getBillNum())*Math.pow(-1,Double.valueOf(todaybill.get(i).getBillType()));
            sum = sum +t;
        }
        double resttoday=totalamoounttoday-sum;
        answer.add(resttoday);
        answer.add(totallimit-sum-Double.valueOf(restTillYesterday.getTotalspend()));
        answer.add(totalamoounttoday);
        return answer;
    }


}
