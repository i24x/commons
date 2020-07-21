package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.mandatory.MandatoryClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/man")
public class _03_MandatoryController {

    @Autowired
    private MandatoryClassA mandatoryClassA;

    @GetMapping("/m0")
    public void method0() {
        mandatoryClassA.method0();
    }

    @GetMapping("/m1")
    public void method1() {
        mandatoryClassA.method1();
    }

    @GetMapping("/m2")
    public void method2() {
        mandatoryClassA.method2();
    }

    @GetMapping("/m3")
    public void method3() {
        mandatoryClassA.method3();
    }

    @GetMapping("/m4")
    public void method4() {
        mandatoryClassA.method4();
    }

}
