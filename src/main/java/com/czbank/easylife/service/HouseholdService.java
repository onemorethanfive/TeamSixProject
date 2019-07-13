package com.czbank.easylife.service;

import com.czbank.easylife.mapper.HouseholdMapper;
import com.czbank.easylife.model.Household;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    @Autowired
    private HouseholdMapper householdMapper;

    public void addHousehold(Household newHousehold){
        householdMapper.addHousehold(newHousehold.getUserId(),newHousehold.getHouseholdId(),newHousehold.getHouseholdName(),newHousehold.getHouseholdNum(),newHousehold.getHouseholdLoc());
    }

    public void deletdHousehold(Household tarHousehold){
        householdMapper.removeHousehold(tarHousehold.getHouseholdId(),tarHousehold.getUserId());
    }

    public void modifyInfo(Household modifyHousehold){
        Household household = householdMapper.findHouseholdByHouseholdId(modifyHousehold.getHouseholdId(),modifyHousehold.getUserId());
        if (modifyHousehold.getHouseholdName() == null)
            modifyHousehold.setHouseholdName(household.getHouseholdName());
        if (modifyHousehold.getHouseholdNum() == null)
            modifyHousehold.setHouseholdNum(household.getHouseholdNum());
        if (modifyHousehold.getHouseholdLoc() == null)
            modifyHousehold.setHouseholdLoc(household.getHouseholdLoc());

        householdMapper.updateHousehold(modifyHousehold.getHouseholdName(),modifyHousehold.getHouseholdNum(),modifyHousehold.getHouseholdLoc());
    }

}
