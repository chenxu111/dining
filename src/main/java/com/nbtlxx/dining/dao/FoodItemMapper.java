package com.nbtlxx.dining.dao;

import com.nbtlxx.dining.pojo.FoodItem;

import java.util.List;

public interface FoodItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodItem record);

    int insertSelective(FoodItem record);

    FoodItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoodItem record);

    int updateByPrimaryKey(FoodItem record);

    List<FoodItem> selectAll();
}