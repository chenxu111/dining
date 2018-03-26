package com.nbtlxx.dining.service.impl;

import com.nbtlxx.dining.dao.*;
import com.nbtlxx.dining.pojo.*;
import com.nbtlxx.dining.service.OrderService;
import com.nbtlxx.dining.vo.OrderParam;
import com.nbtlxx.dining.vo.OrderVo;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxu on 18/3/23.
 */
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    FoodItemMapper foodItemMapper;

    @Autowired
    FoodSaleMapper foodSaleMapper;


    @Override
    public OrderVo findOrderByNoAndUserId(String orderNo, Integer userId) {

        OrderInfo orderInfo = orderInfoMapper.selectByNoUserId(orderNo, userId);
        if (orderInfo== null){
            throw new RuntimeException("");
        }

        FoodItem foodItem = foodItemMapper.selectByPrimaryKey(orderInfo.getId());
        if (foodItem== null){
            throw new RuntimeException("");
        }

        UserInfo userInfo= userInfoMapper.selectByPrimaryKey(userId);
        if (userInfo == null){
            throw new RuntimeException("");
        }

        Integer departmentId = userInfo.getDepartmentId();
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        if (department==null){
            throw new RuntimeException("");
        }

        FoodSale foodSale = foodSaleMapper.selectByPrimaryKey(orderInfo.getFoodSaleId());
        if (foodSale==null){
            throw new RuntimeException("");
        }

        OrderVo orderVo = new OrderVo();
        orderVo.setOrderNo(orderNo);
        orderVo.setDepartmentName(department.getName());
        orderVo.setUserName(userInfo.getName());
        orderVo.setFoodName(foodItem.getFoodName());
        orderVo.setFoodImage(foodItem.getFoodImage());
        orderVo.setFoodPrice(foodSale.getPrice());
        orderVo.setQuantity(orderInfo.getQuantity());
        orderVo.setReserveEnd(0);
        orderVo.setReserveStart(0);
        return orderVo;
    }

    @Override
    public List<OrderVo> listByStatus(Integer status) {

        List<OrderInfo> orderInfoList = orderInfoMapper.selectByStatus(status);

        List<OrderVo> orderVoList=new ArrayList<OrderVo>();

        for (OrderInfo orderInfo: orderInfoList) {
            if (orderInfo == null) {
                throw new RuntimeException("");
            }

            FoodItem foodItem = foodItemMapper.selectByPrimaryKey(orderInfo.getId());
            if (foodItem == null) {
                throw new RuntimeException("");
            }

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(orderInfo.getUserId());
            if (userInfo == null) {
                throw new RuntimeException("");
            }

            Integer departmentId = userInfo.getDepartmentId();
            Department department = departmentMapper.selectByPrimaryKey(departmentId);
            if (department == null) {
                throw new RuntimeException("");
            }

            FoodSale foodSale = foodSaleMapper.selectByPrimaryKey(orderInfo.getFoodSaleId());
            if (foodSale == null) {
                throw new RuntimeException("");
            }

            OrderVo orderVo = new OrderVo();
            orderVo.setOrderNo(orderInfo.getOrderNo());
            orderVo.setDepartmentName(department.getName());
            orderVo.setUserName(userInfo.getName());
            orderVo.setFoodName(foodItem.getFoodName());
            orderVo.setFoodImage(foodItem.getFoodImage());
            orderVo.setFoodPrice(foodSale.getPrice());
            orderVo.setQuantity(orderInfo.getQuantity());
            orderVo.setReserveEnd(0);
            orderVo.setReserveStart(0);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    @Override
    public boolean createOrder(int userId, OrderParam[] orderParams) {

        boolean result=false;
        OrderInfo orderInfo =new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setOrderNo("");
        orderInfo.setCreateTime(new Date());
        orderInfo.setStatus(0);
        orderInfo.setUpdateTime(new Date());

        int effectedNum = orderInfoMapper.insert(orderInfo);
        if (effectedNum<=0){
            throw new RuntimeException("");
        }
        result = true;
        return result;
    }
}