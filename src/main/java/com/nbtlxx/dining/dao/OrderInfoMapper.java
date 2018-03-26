package com.nbtlxx.dining.dao;

import com.nbtlxx.dining.pojo.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    OrderInfo selectByNoUserId(String orderNo, Integer userId);

    List<OrderInfo> selectByStatus(Integer status);
}