package com.nbtlxx.dining.vo;

/**
 * Created by chenxu on 18/3/23.
 */
public class OrderVo {

    String orderNo;
    String userName;
    String departmentName;
    String foodName;
    Integer foodPrice;
    String foodImage;
    Integer reserveStart;
    Integer reserveEnd;
    Integer quantity;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public Integer getReserveStart() {
        return reserveStart;
    }

    public void setReserveStart(Integer reserveStart) {
        this.reserveStart = reserveStart;
    }

    public Integer getReserveEnd() {
        return reserveEnd;
    }

    public void setReserveEnd(Integer reserveEnd) {
        this.reserveEnd = reserveEnd;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
