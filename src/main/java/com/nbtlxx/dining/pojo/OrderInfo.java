package com.nbtlxx.dining.pojo;

import java.util.Date;

public class OrderInfo {
    private Integer id;

    private String orderNo;

    private String qrcode;

    private Integer userId;

    private Integer foodSaleId;

    private Integer quantity;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public OrderInfo(Integer id, String orderNo, String qrcode, Integer userId, Integer foodSaleId, Integer quantity, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.qrcode = qrcode;
        this.userId = userId;
        this.foodSaleId = foodSaleId;
        this.quantity = quantity;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodSaleId() {
        return foodSaleId;
    }

    public void setFoodSaleId(Integer foodSaleId) {
        this.foodSaleId = foodSaleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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