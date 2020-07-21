package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.requiresnew.RequiresNewClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/rn")
public class _04_RequiresNewController {

    @Autowired
    private RequiresNewClassA requiresNewClassA;

    @GetMapping("/m1")
    public void method1() {
        requiresNewClassA.method1();
    }

    @GetMapping("/m2")
    public void method2() {
        requiresNewClassA.method2();
    }

    @GetMapping("/m3")
    public void method3() {
        requiresNewClassA.method3();
    }

}
