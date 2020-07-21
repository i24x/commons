package com.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.service.required.ClassA;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 13:01
 * @author: 十一
 */
@RestController
@RequestMapping("/rq")
public class _01_RequiredController {

    @Autowired
    private ClassA classA;

    @GetMapping("/m")
    public void method() {
        classA.method();
    }

    // http://localhost:8080/require/mExp1
    @GetMapping("/mexp1")
    public void mexp1() {
        classA.mexp1();
    }

    // Transaction silently rolled back
    // because it has been marked as rollback-only
    @GetMapping("/mexp2")
    public void mexp2() {
        classA.mexp2();

    }

    /**
     * 不能在Transactional方法中捕获，这里可以捕获到
     */
    @GetMapping("/mexp3")
    public void mexp3() {
        try {
            classA.mexp1();// AB 均回滚
        } catch (Exception e) {
            System.out
                .println("<=========================" + e.getMessage() + "=========================================");
        }
    }

    /**
     * REQUIRED
     */
    @GetMapping("/m3exp4")
    public void method34() {
        classA.m3();// 正常提交
        classA.exp4();// 回滚
    }

    /**
     * REQUIRED m3 正常提交 exp4 异常 m3 不回滚 exp4 回滚
     */
    @GetMapping("/m3callexp4")
    public void method4in3() {
        classA.m3callexp4();// 回滚
    }

    @GetMapping("/m3callexp5")
    public void method4in5() {
        classA.m3callexp5();
    }
}
