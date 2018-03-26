package com.nbtlxx.dining.dto;

import com.nbtlxx.dining.enums.FoodStateEnum;
import com.nbtlxx.dining.pojo.FoodItem;

import java.util.List;

/**
 * Created by chenxu on 18/3/19.
 */
public class FoodExecution {

    private int state;
    private String stateInfo;
    private int count;
    private FoodItem foodItem;
    private List<FoodItem> foodItemList;

    public FoodExecution(){

    }

    public FoodExecution(FoodStateEnum foodStateEnum){
        this.state = foodStateEnum.getState();
        this.stateInfo= foodStateEnum.getStateInfo();
    }


    public FoodExecution(FoodStateEnum foodStateEnum, FoodItem foodItem){
        this.state = foodStateEnum.getState();
        this.stateInfo= foodStateEnum.getStateInfo();
        this.foodItem= foodItem;
    }

    public FoodExecution(FoodStateEnum foodStateEnum, List<FoodItem> foodItemList){
        this.state = foodStateEnum.getState();
        this.stateInfo= foodStateEnum.getStateInfo();
        this.foodItemList= foodItemList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }
}


