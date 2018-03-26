package com.nbtlxx.dining.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chenxu on 18/3/18.
 */
@Controller
@RequestMapping(value = "/admin/")
public class AdminController {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "foodlist",method = RequestMethod.GET)
    public String foodlist(){
        return "admin/foodlist";
    }
}
