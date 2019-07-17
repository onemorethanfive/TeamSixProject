package com.czbank.easylife.service;
import com.czbank.easylife.mapper.BillMapper;
import com.czbank.easylife.mapper.UserSpendMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.DateBalanceMap;
import com.czbank.easylife.model.UserSpend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UserSpendService {
    @Autowired
    private UserSpendMapper userSpendMapper;
    @Autowired
    private BillMapper billMapper;
//list:第一项今日剩余限额，第二项总剩余余额,第三项今日总可用余额
    //输入：userid，今天日期，限额到今天天数，限额总天数，限额金额
    public Map<String,Object> getLimitReminder(String userId, String today ) throws ParseException {
        List<Bill> bills  = billMapper.findBillsByDateUser(today,userId);
        UserSpend userSpend = userSpendMapper.findTotalspendByIdAndDate(userId,today);
        Double totalday = Double.valueOf(userSpend.getDatetotal());
        Double totallimit = Double.valueOf(userSpend.getLimittotal());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date yesterday = sdf.parse(userSpend.getDate());
        Date startDay = sdf.parse(userSpend.getStart());
        double durationDays = yesterday.getTime()-startDay.getTime();
        durationDays = durationDays/(24*60*60*1000);

        double limitToday = (totallimit-Double.valueOf(userSpend.getTotalspend()))/(totalday-durationDays);
        double sum = 0;
        for (int i=0;i<bills.size();i++){
            double t=Double.valueOf(bills.get(i).getBillNum())*Integer.valueOf(bills.get(i).getBillType());
            sum = sum +t;
        }
        double restToday=limitToday-sum;

        Map<String,Object> result = new HashMap<>();
        result.put("restToday",String.valueOf(restToday));
        result.put("limitTotal",String.valueOf(totallimit));
        result.put("restTotal",String.valueOf(totallimit-sum-Double.valueOf(userSpend.getTotalspend())));
        result.put("limitToday",String.valueOf(limitToday));
        result.put("startDate",sdf.format(startDay));
        result.put("pastDays",durationDays);
        result.put("totalDay",totalday);
        return result;
    }

    public int updateUserSpend(String userID, String datelimit, String totallimit){
        return userSpendMapper.updateUserSpend(userID,datelimit,totallimit);
    }

}


