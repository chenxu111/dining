package com.nbtlxx.dining.controller.admin;

import com.nbtlxx.dining.common.ServerResponse;
import com.nbtlxx.dining.pojo.FoodItem;
import com.nbtlxx.dining.pojo.FoodSale;
import com.nbtlxx.dining.service.FoodSaleService;
import com.nbtlxx.dining.vo.FoodSaleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxu on 18/3/19.
 */
@Controller
@RequestMapping(value = "admin/foodsale/")
public class FoodSaleManageController {

    Logger logger = LoggerFactory.getLogger(FoodSaleManageController.class);
    @Autowired
    FoodSaleService foodSaleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listFoodSale(@RequestParam(value = "status") int status) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<FoodSaleVo> foodSaleVoList = foodSaleService.listByStatus(status);
        modelMap.put("result", "success");
        modelMap.put("foodSaleList", foodSaleVoList);
        return modelMap;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> detail(@RequestParam(value = "id") int id) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        FoodSaleVo foodSale = foodSaleService.detail(id);
        modelMap.put("result", "success");
        modelMap.put("foodSale", foodSale);
        return modelMap;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addFoodSale(@RequestParam(value = "foodid") Integer foodId,
                                           @RequestParam(value = "price") Integer price,
                                           @RequestParam(value = "status") int status,
                                           @RequestParam(value = "start") Long start,
                                           @RequestParam(value = "end") Long end) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        FoodSale foodSale = new FoodSale();
        foodSale.setFoodId(foodId);
        foodSale.setPrice(price);
        foodSale.setSaleStart(new Date(start));
        foodSale.setSaleEnd(new Date(end));
        foodSale.setCreateTime(new Date());
        foodSale.setUpdateTime(new Date());
        foodSale.setStatus(status);
        boolean result = false;
        try {
            result = foodSaleService.addFoodSale(foodSale);
            modelMap.put("success", result);
        } catch (Exception e) {
            modelMap.put("success", result);
        }
        return modelMap;
    }

    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateFoodSale(@RequestParam(value = "foodsaleid") Integer foodSaleId, @RequestParam(value = "status") int status) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        try {
            boolean result = foodSaleService.updateStatus(foodSaleId, status);
            modelMap.put("success", result);
        } catch (Exception e) {
            modelMap.put("success", false);
        }
        return modelMap;
    }

    @RequestMapping(value = "updateFood", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateFoodSale(@RequestParam(value = "foodsaleid") Integer foodSaleId,
                                              @RequestParam(value = "foodname") String foodName,
                                              @RequestParam(value = "desc") String desc,
                                              @RequestParam(value = "price") Integer price,
                                              @RequestParam(value = "foodimg") String foodimg) {
        logger.info("start to process updateFoodSale");

        Map<String, Object> modelMap = new HashMap<String, Object>();

        try {
            boolean result = foodSaleService.updateFood(foodSaleId, foodName, desc, price, foodimg);
            modelMap.put("success", result);
        } catch (Exception e) {
            modelMap.put("success", false);
        }
        logger.info("end to process updateFoodSale");
        return modelMap;
    }
}