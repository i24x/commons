package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.notsupported.NotSupportedClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/ns")
public class _05_NotSupportedController {

    @Autowired
    private NotSupportedClassA notSupportedClassA;

    @GetMapping("/m1")
    public void method1() {
        notSupportedClassA.method1();
    }

    @GetMapping("/m2")
    public void method2() {
        notSupportedClassA.method2();
    }

}
