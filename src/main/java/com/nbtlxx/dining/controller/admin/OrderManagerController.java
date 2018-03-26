package com.nbtlxx.dining.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxu on 18/3/19.
 */
@Controller
@RequestMapping(value = "admin/order/")
public class OrderManagerController
{

//    @Autowired


    @RequestMapping(value = "listbystatus",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listOrderByStatus(@RequestParam(value = "status")int status){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",true);
        return modelMap;
    }


}
