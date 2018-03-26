package com.nbtlxx.dining.controller.admin;

import com.nbtlxx.dining.common.ImageHolder;
import com.nbtlxx.dining.common.ServerResponse;
import com.nbtlxx.dining.dto.FoodExecution;
import com.nbtlxx.dining.enums.FoodStateEnum;
import com.nbtlxx.dining.exception.ShopOperationException;
import com.nbtlxx.dining.pojo.FoodItem;
import com.nbtlxx.dining.pojo.UserInfo;
import com.nbtlxx.dining.service.FoodService;
import com.nbtlxx.dining.util.HttpServletRequestUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxu on 18/3/19.
 */
@Controller
@RequestMapping(value = "admin/food/")
public class FoodManageController {

    @Autowired
    FoodService foodService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> listFood(HttpServletRequest request) {
        foodService.list();
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addFood(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        // 1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "foodStr");
        ObjectMapper mapper = new ObjectMapper();
        FoodItem foodItem = null;
        try {
            foodItem = mapper.readValue(shopStr, FoodItem.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile foodImage = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            foodImage = (CommonsMultipartFile) multipartHttpServletRequest.getFile("foodImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        // 2.添加食物
        if (foodItem != null && foodItem != null) {
//            UserInfo owner = (UserInfo) request.getSession().getAttribute("user");
//            foodItem.setOwner(owner);
            FoodExecution fe;
            try {
                ImageHolder imageHolder = new ImageHolder(foodImage.getOriginalFilename(), foodImage.getInputStream());
                fe = foodService.addFood(foodItem, imageHolder);
                if (fe.getState() == FoodStateEnum.ONLINE.getState()) {
                    modelMap.put("success", true);
                    // 该用户可以操作的食品列表
                    @SuppressWarnings("unchecked")
                    List<FoodItem> foodItemList = (List<FoodItem>) request.getSession().getAttribute("foodItemList");
                    if (foodItemList == null || foodItemList.size() == 0) {
                        foodItemList = new ArrayList<FoodItem>();
                    }
                    foodItemList.add(fe.getFoodItem());
                    request.getSession().setAttribute("foodItemList", foodItemList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", fe.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入食品信息");
            return modelMap;
        }
    }

    @RequestMapping(value = "updateFood", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateFood() {
        return null;
    }

    @RequestMapping(value = "deleteFood", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateFood(@RequestParam(value = "foodId") int foodId) {
        return null;
    }
}
