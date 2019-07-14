package com.czbank.easylife.service;

import com.czbank.easylife.mapper.DateBalanceMapMapper;
import com.czbank.easylife.model.Bill;
import com.czbank.easylife.model.DateBalanceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DateBalanceMapService {
    @Autowired
    private DateBalanceMapMapper dateBalanceMapMapper;

    SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
    String curday = day.format(new Date());
    int lastYearInt = Integer.parseInt(curday)-10000;

    public List<DateBalanceMap> getBalanceByUser(String user_id){
        List<DateBalanceMap> bs = dateBalanceMapMapper.getBalanceByUser(user_id,lastYearInt);
        return bs;
    }
}
