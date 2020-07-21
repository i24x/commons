package com.lsl.commons.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lsl.commons.config.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.lsl.commons.config.boot.aware.SpringContextUtil;
import com.lsl.commons.service.IOrderService;

@Controller
@RequestMapping("/api")
public class BaseController {

    @RequestMapping(value = {"/getOrder/{name}"}, method = {RequestMethod.GET})
    public @ResponseBody String getOrder(HttpServletRequest request, HttpServletResponse response,
        @PathVariable("name") String name) {
        IOrderService bean = SpringContextUtil.getBean("OrderService", IOrderService.class);
        bean.updateOrder();
        return name + "MVTCAT";
    }

    @Log
    @GetMapping("/test")
    public @ResponseBody String test(String a) {
        return "a->"+a;
    }

}
