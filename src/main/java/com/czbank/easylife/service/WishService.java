package com.czbank.easylife.service;

import com.czbank.easylife.mapper.WishMapper;
import com.czbank.easylife.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishService {
    @Autowired
private WishMapper wishMapper;

    public List<Wish> getWishes(String userId){
        return wishMapper.findWishByUserId(userId);
    }

    public Wish getWish(String wishId){return wishMapper.findWishByWishId(wishId);}
    public void addWish(Wish newWish){
        wishMapper.addWish(newWish.getWishId(),newWish.getUserId(),newWish.getWishName(),newWish.getWishNum(),
                newWish.getStarDate(),newWish.getTargetDate(),newWish.getWishState(),
                newWish.getCurrentState(),newWish.getWishRemark(),newWish.getInterestRate(),
                newWish.getInterestId(),newWish.getWishBillId());
    }

    public void removeWish(Wish tarWish){
        wishMapper.removeWish(tarWish.getUserId());
    }

    //前端传输数据时 把每个条目都加到json ,不需要修改的""

    public void modifyInfo(Wish modifyWish){
        Wish wish = wishMapper.findWishByWishId(modifyWish.getWishId());
        if (modifyWish.getWishName().equals(""))
            modifyWish.setWishName(wish.getWishName());
        if (modifyWish.getWishNum().equals(""))
            modifyWish.setWishNum(wish.getWishNum());
        if (modifyWish.getStarDate().equals(""))
            modifyWish.setStarDate(wish.getStarDate());
        if (modifyWish.getTargetDate().equals(""))
            modifyWish.setTargetDate(wish.getTargetDate());
        if (modifyWish.getWishState().equals(""))
            modifyWish.setWishState(wish.getWishState());
        if (modifyWish.getCurrentState().equals(""))
            modifyWish.setCurrentState(wish.getCurrentState());
        if (modifyWish.getInterestRate().equals(""))
            modifyWish.setInterestRate(wish.getInterestRate());
        if (modifyWish.getInterestId().equals(""))
            modifyWish.setInterestId(wish.getInterestId());
        if (modifyWish.getWishBillId().equals(""))
            modifyWish.setWishBillId(wish.getWishBillId());

        wishMapper.updateWish(modifyWish.getWishId(),modifyWish.getUserId(),modifyWish.getWishName(),
                modifyWish.getWishNum(),modifyWish.getStarDate(),modifyWish.getTargetDate(),
                modifyWish.getWishState(),modifyWish.getCurrentState(),modifyWish.getWishRemark(),
                modifyWish.getInterestRate(),modifyWish.getInterestId(),modifyWish.getWishBillId());
    }

}
