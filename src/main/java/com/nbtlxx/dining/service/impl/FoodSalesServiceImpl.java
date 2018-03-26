package com.nbtlxx.dining.service.impl;

import com.nbtlxx.dining.dao.FoodItemMapper;
import com.nbtlxx.dining.dao.FoodSaleMapper;
import com.nbtlxx.dining.pojo.FoodItem;
import com.nbtlxx.dining.pojo.FoodSale;
import com.nbtlxx.dining.service.FoodSaleService;
import com.nbtlxx.dining.vo.FoodSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxu on 18/3/19.
 */
@Service
public class FoodSalesServiceImpl implements FoodSaleService {

    @Autowired
    FoodSaleMapper foodSaleMapper;

    @Autowired
    FoodItemMapper foodItemMapper;

    @Override
    public List<FoodSaleVo> listByStatus(int status) {
        try {
            List<FoodSale> foodSaleList = foodSaleMapper.selectByStatus(status);
            if (foodSaleList==null){
                throw new RuntimeException("");
            }
            return buildVo(foodSaleList);
        }catch (Exception e){
            System.err.print(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateStatus(Integer foodSaleId, int status) {
        FoodSale foodSale = null;
        try {
            foodSale = foodSaleMapper.selectByPrimaryKey(foodSaleId);
            if (foodSale == null) {
                throw new RuntimeException("");
            }
        } catch (RuntimeException e) {
            System.err.print(e.getMessage());
        }

        foodSale.setStatus(status);
        foodSale.setUpdateTime(new Date());

        try {
            int effectedNum = foodSaleMapper.updateByPrimaryKey(foodSale);
            if (effectedNum > 0) {
                return true;
            }
        } catch (RuntimeException e) {
            System.err.print(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addFoodSale(FoodSale foodSale) {
        foodSale.setCreateTime(new Date());
        foodSale.setUpdateTime(new Date());
        try {
            int effectedNum = foodSaleMapper.insert(foodSale);
            if (effectedNum > 0) {
                return true;
            }
        } catch (RuntimeException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean updateFood(Integer foodSaleId, String foodName, String desc, Integer price, String foodimg) {
        FoodSale foodSale = null;
        try {
            foodSale = foodSaleMapper.selectByPrimaryKey(foodSaleId);
            if (foodSale == null) {
                throw new RuntimeException("");
            }

            int foodId = foodSale.getFoodId();
            FoodItem foodItem = foodItemMapper.selectByPrimaryKey(foodId);
            if (foodItem ==null){
                throw new RuntimeException("");
            }
            foodItem.setFoodName(foodName);
            foodItem.setFoodDesc(desc);
            foodItem.setFoodImage(foodimg);
            foodItem.setUpdateTime(new Date());

            int effectedNum = foodItemMapper.updateByPrimaryKey(foodItem);
            if (effectedNum<=0){
                throw new RuntimeException("");
            }

            foodSale.setPrice(price);
            foodSale.setUpdateTime(new Date());

            effectedNum = foodSaleMapper.updateByPrimaryKey(foodSale);
            if (effectedNum<=0){
                throw new RuntimeException("");
            }
            return true;
        } catch (RuntimeException e) {
            System.err.print(e.getMessage());
        }
        return false;
    }

    private List<FoodSaleVo> buildVo(List<FoodSale> list) {

        List<FoodSaleVo> foodSaleVoList = new ArrayList<FoodSaleVo>();

        for (FoodSale foodSale : list) {
            FoodSaleVo foodSaleVo = new FoodSaleVo();
            foodSaleVo.setId(foodSale.getId());

            FoodItem foodItem = foodItemMapper.selectByPrimaryKey(foodSale.getFoodId());
            if (foodItem != null) {
                foodSaleVo.setFoodName(foodItem.getFoodName());
                foodSaleVo.setImageUrl(foodItem.getFoodImage());
            }
            foodSaleVo.setStatus(foodSaleVo.getStatus());
            foodSaleVo.setPrice((double) foodSale.getPrice());
            foodSaleVoList.add(foodSaleVo);
        }
        return foodSaleVoList;
    }
}
