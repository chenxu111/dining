package com.nbtlxx.dining.pojo;

import java.util.Date;

public class FoodSale {
    private Integer id;

    private Integer foodId;

    private Date saleStart;

    private Date saleEnd;

    private Integer price;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public FoodSale(Integer id, Integer foodId, Date saleStart, Date saleEnd, Integer price, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.foodId = foodId;
        this.saleStart = saleStart;
        this.saleEnd = saleEnd;
        this.price = price;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FoodSale() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Date getSaleStart() {
        return saleStart;
    }

    public void setSaleStart(Date saleStart) {
        this.saleStart = saleStart;
    }

    public Date getSaleEnd() {
        return saleEnd;
    }

    public void setSaleEnd(Date saleEnd) {
        this.saleEnd = saleEnd;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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