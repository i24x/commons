package com.transaction.service.required;

import java.sql.Connection;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.common.TransactionUtils;
import com.transaction.model.User;
import com.transaction.repository.UserRepository;

/**
 * @description: 测试事务的传播性
 *
 *               1. Propagation.REQUIRED
 * @date: 2019-06-24 12:42
 * @author: 十一
 */
@Service
public class ClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassB classB;

    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 在类A调用方法1（有@Transaction），同时调用类B方法1（无@Transaction） 类B方法1抛异常，全部回滚 事务传播特性是：REQUIRED，在一个事务的上下文中，都会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method() {
        User user = new User();
        user = userRepository.save(user);
    }

    /**
     * 在类A调用方法1（有@Transaction），同时调用类B方法1（无@Transaction） 类B方法1抛异常，全部回滚 事务传播特性是：REQUIRED，在一个事务的上下文中，都会回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void mexp1() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        classB.exp1();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void mexp2() {
        Connection connection = transactionUtils.getConnection();
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        try {
            System.out.println("classB.exp1 标记 REQUIRED 为回滚 connection " + connection);
            classB.exp1();
        } catch (Exception e) {
            System.out.println("<====================== 捕获异常： " + e.getCause());
            System.out.println("<====================== 捕获异常： " + e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void m3() {
        transactionUtils.getConnection();
        User user = new User();
        user.setName("REQUIRED m3");
        user = userRepository.save(user);
        // if(user.getId()!=null){
        // throw new RuntimeException("REQUIRED m3 throw RuntimeException");
        // }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void exp4() {
        transactionUtils.getConnection();
        User user = new User();
        user.setName("REQUIRED m4 throw RuntimeException");
        user = userRepository.save(user);
        if (user.getId() != null) {
            throw new RuntimeException("REQUIRED m3 throw RuntimeException");
        }
    }

    // 去掉 Transactional 效果
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void m3callexp4() {
        m3();
        exp4();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void exp5() {
        transactionUtils.getConnection();
        User user = new User();
        user.setName("REQUIRED m5 throw RuntimeException");
        user = userRepository.save(user);
        if (user.getId() != null) {
            throw new RuntimeException("REQUIRED m3 throw RuntimeException");
        }
    }

    // spring事物失效探讨
    public void m3callexp5() {
        ClassA proxy = (ClassA)AopContext.currentProxy();
        proxy.m3();// 事物正常提交 exp5不回滚m3
        boolean aopProxy = AopUtils.isAopProxy(this);
        System.out.println("当前对象是否为代理对象" + aopProxy);
        proxy.exp5();// 事物失效
    }

    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    // public void methodA(){
    //
    // }
    //
    // /**
    // * 这里调用methodA() 的事务将会失效
    // */
    // public void methodB(){
    // this.methodA();
    // }
}
