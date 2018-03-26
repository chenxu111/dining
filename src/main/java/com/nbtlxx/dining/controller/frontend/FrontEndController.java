package com.nbtlxx.dining.controller.frontend;

import com.nbtlxx.dining.common.ServerResponse;
import com.nbtlxx.dining.pojo.FoodItem;
import com.nbtlxx.dining.service.FoodSaleService;
import com.nbtlxx.dining.service.OrderService;
import com.nbtlxx.dining.vo.FoodSaleVo;
import com.nbtlxx.dining.vo.OrderParam;
import com.nbtlxx.dining.vo.OrderVo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxu on 18/3/17.
 */
@Controller
@RequestMapping(value = "/frontend/")
public class FrontEndController {


    @Autowired
    FoodSaleService foodSaleService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "frontend/index";
    }

    @RequestMapping(value = "foodlist",method = RequestMethod.GET)
    public String foodList(){
        return "frontend/foodlist";
    }

    @RequestMapping(value = "getfoodlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getFoodList(){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        int status =0;
        try {
            List<FoodSaleVo> orderVoList=foodSaleService.listByStatus(status);
            modelMap.put("success",true);
            modelMap.put("orderList",orderVoList);
        }catch (Exception e){
            modelMap.put("success",false);
        }
        return modelMap;
    }

   @RequestMapping(value = "createorder",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> createOrder(@RequestBody OrderParam[] orderParams){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        try {
            int userId=1;
            boolean result = orderService.createOrder(userId, orderParams);
            modelMap.put("success",result);
        }catch (Exception e){
            modelMap.put("success",false);
        }
        return modelMap;
    }

    @RequestMapping(value = "createreserve",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> createReserve(@RequestBody OrderParam[] orderParams){
        Map<String,Object> modelMap=new HashMap<String,Object>();
        try {
            int userId=1;
            boolean result = orderService.createOrder(userId, orderParams);
            modelMap.put("success",result);
        }catch (Exception e){
            modelMap.put("success",false);
        }
        return modelMap;
    }
}