package com.transaction.service.supports;

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
public class SupportClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupportClassB supportClassB;

    /**
     * 在类A调用方法1（有@Transaction），同时调用类B方法1（无@Transaction） 类B方法1抛异常，全部回滚 事务传播特性是：REQUIRED，在一个事务的上下文中，都会回滚
     */

    public void method1() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        supportClassB.method1();
    }

    // SUPPORTS ->REQUIRED
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public void method2() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = supportClassB.method2();
        if (coffee.getId() > 0) {
            throw new RuntimeException("测试 propagation = Propagation.SUPPORTS ");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method3() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = supportClassB.method3();

    }

}
