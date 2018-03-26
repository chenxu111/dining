package com.nbtlxx.dining.service.impl;

import com.nbtlxx.dining.common.ImageHolder;
import com.nbtlxx.dining.dao.FoodItemMapper;
import com.nbtlxx.dining.dto.FoodExecution;
import com.nbtlxx.dining.enums.FoodStateEnum;
import com.nbtlxx.dining.exception.FoodItemException;
import com.nbtlxx.dining.pojo.FoodItem;
import com.nbtlxx.dining.service.FoodService;
import com.nbtlxx.dining.util.ImageUtil;
import com.nbtlxx.dining.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenxu on 18/3/19.
 */
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodItemMapper foodItemMapper;

    @Override
    public FoodExecution list() {

        foodItemMapper.selectAll();

        return null;
    }

    @Override
    public FoodExecution addFood(FoodItem foodItem, ImageHolder imageHolder) {
        if (foodItem == null) {
            return new FoodExecution(FoodStateEnum.NULL);
        }

        try {
            foodItem.setCreateTime(new Date());
            foodItem.setUpdateTime(new Date());

            int effectNum = foodItemMapper.insert(foodItem);
            if (effectNum <= 0) {
                throw new FoodItemException("add food item error ");
            } else {
                if (imageHolder.getInputStream() != null) {
                    try {
                        addFoodImage(foodItem, imageHolder);
                    } catch (Exception e) {
                        throw new FoodItemException("add food image error " + e.getMessage());
                    }

                    if (effectNum <= 0) {
                        throw new FoodItemException("update image fail");
                    }
                }
            }
        } catch (Exception e) {
            throw new FoodItemException("add food error " + e.getMessage());
        }
        return new FoodExecution(FoodStateEnum.ONLINE, foodItem);
    }

    private void addFoodImage(FoodItem foodItem, ImageHolder thumbnail) {
        // 获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(foodItem.getId());
        String foodImageAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        foodItem.setFoodImage(foodImageAddr);
    }
}
