package com.lsl.commons.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsl.commons.lang.utils.system.management.JvmUtil;
import com.lsl.commons.lang.utils.system.management.SystemInfoBean;
import com.lsl.commons.lang.utils.system.management.SystemInfoUtils;

@Controller
@RequestMapping("/system")
public class SystemController {
    @RequestMapping(value = {"/info"}, method = {RequestMethod.GET})
    public @ResponseBody SystemInfoBean info(HttpServletRequest request, HttpServletResponse response) {
        return SystemInfoUtils.getInstance().getSystemInfo();
    }

    @RequestMapping(value = {"/pid"}, method = {RequestMethod.GET})
    public @ResponseBody Integer pid(HttpServletRequest request, HttpServletResponse response) {
        return JvmUtil.getVMPid();
    }
}
