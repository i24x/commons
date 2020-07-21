package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.nested.NestedClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/nested")
public class _07_NestedController {

    @Autowired
    private NestedClassA nestedClassA;

    @GetMapping("/m1")
    public void method1() {
        nestedClassA.method1();
    }

    @GetMapping("/m2")
    public void method2() {
        nestedClassA.method2();
    }

    // JPA不支持嵌套事物
    @GetMapping("/m3")
    public void method3() {
        nestedClassA.method3();
    }

    @GetMapping("/m4")
    public void method4() {
        nestedClassA.method4();
    }

    @GetMapping("/m5")
    public void method5() {
        nestedClassA.method5();
    }

}
