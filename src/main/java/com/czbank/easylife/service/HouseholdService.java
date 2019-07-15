package com.czbank.easylife.service;

import com.czbank.easylife.mapper.HouseholdMapper;
import com.czbank.easylife.model.Household;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdService {
    @Autowired
    private HouseholdMapper householdMapper;

    public List<Household> getHouseholdByUser(String userId){
        return householdMapper.findHouseholdByUserId(userId);
    }

    public Household getHousehold(String userId, String householdId){
        return householdMapper.findHouseholdByHouseholdId(householdId,userId);
    }

    public List<Household> getHouseholds(String userId){
        return householdMapper.findHouseholdByUserId(userId);
    }

    public void addHousehold(Household newHousehold){
        householdMapper.addHousehold(newHousehold.getUserId(),newHousehold.getHouseholdId(),newHousehold.getHouseholdName(),newHousehold.getHouseholdNum(),newHousehold.getHouseholdLoc());
    }

    public void deletdHousehold(Household tarHousehold){
        householdMapper.removeHousehold(tarHousehold.getHouseholdId(),tarHousehold.getUserId());
    }

    //前端传输数据时 把每个条目都加到json ,不需要修改的""

    public void modifyInfo(Household modifyHousehold){
        Household household = householdMapper.findHouseholdByHouseholdId(modifyHousehold.getHouseholdId(),modifyHousehold.getUserId());
        if (modifyHousehold.getHouseholdName().equals(""))
            modifyHousehold.setHouseholdName(household.getHouseholdName());
        if (modifyHousehold.getHouseholdNum().equals(""))
            modifyHousehold.setHouseholdNum(household.getHouseholdNum());
        if (modifyHousehold.getHouseholdLoc().equals(""))
            modifyHousehold.setHouseholdLoc(household.getHouseholdLoc());

        householdMapper.updateHousehold(modifyHousehold.getHouseholdName(),modifyHousehold.getHouseholdNum(),modifyHousehold.getHouseholdLoc(),modifyHousehold.getHouseholdId(),modifyHousehold.getUserId());
    }

}
