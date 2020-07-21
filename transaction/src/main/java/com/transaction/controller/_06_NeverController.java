package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.never.NeverClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/never")
public class _06_NeverController {

    @Autowired
    private NeverClassA neverClassA;

    @GetMapping("/m1")
    public void method1() {
        neverClassA.method1();
    }

    @GetMapping("/m2")
    public void method2() {
        neverClassA.method2();
    }

}
