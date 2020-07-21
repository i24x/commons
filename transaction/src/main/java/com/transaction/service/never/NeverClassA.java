package com.transaction.service.never;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class NeverClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NeverClassB neverClassB;

    // Existing transaction found for transaction marked with propagation 'never'
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method1() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        neverClassB.method1();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method2() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        try {
            // 一进入B就报错
            neverClassB.method1();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
