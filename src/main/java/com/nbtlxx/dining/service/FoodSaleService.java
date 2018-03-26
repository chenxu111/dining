package com.nbtlxx.dining.service;

import com.nbtlxx.dining.pojo.FoodSale;
import com.nbtlxx.dining.vo.FoodSaleVo;

import java.util.List;

/**
 * Created by chenxu on 18/3/19.
 */
public interface FoodSaleService {

    List<FoodSaleVo> listByStatus(int status);

    boolean updateStatus(Integer foodSaleId, int status);

    boolean addFoodSale(FoodSale foodSale);

    boolean updateFood(Integer foodSaleId, String foodName, String desc, Integer price, String foodimg);
}
