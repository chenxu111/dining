package com.nbtlxx.dining.controller.admin;

import com.nbtlxx.dining.service.OrderService;
import com.nbtlxx.dining.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxu on 18/3/23.
 */
@Controller
@RequestMapping(value = "admin/order/")
public class FoodOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "listByStatus",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listByStatus(@RequestParam(value = "status")int status){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int userId = 1;
        try {
            List<OrderVo> orderVoList = orderService.listByStatus(status);
            modelMap.put("success",true);
            modelMap.put("orderVoList",orderVoList);
        }catch (Exception e){
            modelMap.put("success",false);
        }
        return modelMap;
    };

    @RequestMapping(value = "detail",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> orderDetail(@RequestParam(value = "orderno")String orderNo){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int userId = 1;
        try {
            OrderVo orderVo = orderService.findOrderByNoAndUserId(orderNo, userId);
            modelMap.put("success",true);
            modelMap.put("order",orderVo);
        }catch (Exception e){
            modelMap.put("success",false);
        }
        return modelMap;
    };
}