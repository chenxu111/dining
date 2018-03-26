package com.nbtlxx.dining.service;

import com.nbtlxx.dining.common.ImageHolder;
import com.nbtlxx.dining.dto.FoodExecution;
import com.nbtlxx.dining.pojo.FoodItem;

/**
 * Created by chenxu on 18/3/19.
 */
public interface FoodService {


    FoodExecution addFood(FoodItem foodItem, ImageHolder imageHolder);

    FoodExecution list();
}
