package com.nbtlxx.dining.pojo;

import java.util.Date;

public class FoodItem {
    private Integer id;

    private String foodName;

    private String foodImage;

    private Integer status;

    private String foodDesc;

    private Date createTime;

    private Date updateTime;

    public FoodItem(Integer id, String foodName, String foodImage, Integer status, String foodDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.status = status;
        this.foodDesc = foodDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FoodItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage == null ? null : foodImage.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc == null ? null : foodDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}