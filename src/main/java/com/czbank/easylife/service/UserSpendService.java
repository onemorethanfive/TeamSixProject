package com.czbank.easylife.service;
import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.mapper.UserSpendMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.UserSpend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
@Service
public class UserSpendService {
    @Autowired
    private UserSpendMapper userSpendMapper;
    @Autowired
    private BillMapper billMapper;
//list:第一项今日剩余限额，第二项总剩余余额。
    //输入：userid，今天日期，限额到今天天数，限额总天数，限额金额
    public List<Double> getLimitReminder(String userId, String today,double pastday,double totalday , double totallimit ){
        List<Bill> todaybill  = billMapper.findBillsByDateUser(today,userId);
        UserSpend restTillYesterday = userSpendMapper.findTotalspendByIdAndDate(userId,today);
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
        answer.add(resttoday+Double.valueOf(restTillYesterday.getTotalspend()));
        return answer;
    }


}
