package com.transaction.service.nested;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.model.Coffee;
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
public class NestedClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NestedClassB classB;

    /**
     * 在类A调用方法1（有@Transaction），同时调用类B方法1（无@Transaction） 类B方法1抛异常，全部回滚 事务传播特性是：REQUIRED，在一个事务的上下文中，都会回滚
     * 用于解决外层方法没有添加事物注解导致内层方法事物无效问题
     */
    public void method1() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        classB.method1();
    }

    // 都不回滚
    public void method2() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = classB.method2();
        if (coffee.getId() > 1) {
            throw new RuntimeException("测试异常");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method3() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = classB.method3();
        if (coffee.getId() > 1) {
            throw new RuntimeException("测试异常");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method4() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = classB.method4();
    }

    // @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public void method5() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        try {
            Coffee coffee = classB.method5();
        } catch (Exception e) {
            System.out.println("捕获异常");
        }
    }

}
