package com.nbtlxx.dining.service;

import com.nbtlxx.dining.pojo.OrderInfo;
import com.nbtlxx.dining.vo.OrderParam;
import com.nbtlxx.dining.vo.OrderVo;

import java.util.List;

/**
 * Created by chenxu on 18/3/23.
 */
public interface OrderService {
    OrderVo findOrderByNoAndUserId(String orderNo, Integer userId);

    List<OrderVo> listByStatus(Integer status);

    boolean createOrder(int userId, OrderParam[] orderParams);
}
