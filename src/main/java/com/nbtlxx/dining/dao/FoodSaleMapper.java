package com.nbtlxx.dining.dao;

import com.nbtlxx.dining.pojo.FoodSale;

import java.util.List;

public interface FoodSaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FoodSale record);

    int insertSelective(FoodSale record);

    FoodSale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FoodSale record);

    int updateByPrimaryKey(FoodSale record);

    List<FoodSale> selectByStatus(int status);
}