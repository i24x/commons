package com.transaction.service.requiresnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.model.Coffee;
import com.transaction.model.User;
import com.transaction.repository.UserRepository;

/**
 * @description: 测试事务的传播性
 *               <p>
 *               1. Propagation.REQUIRED
 * @date: 2019-06-24 12:42
 * @author: 十一
 */
@Service
public class RequiresNewClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequiresNewClassB requiresNewClassB;

    /**
     * 类B方法1抛异常，全部回滚
     */
    // @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)

    /**
     * A根据自己情况回滚 B提交
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method1() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = requiresNewClassB.method1();
        if (coffee.getId() > 0) {
            throw new RuntimeException("异常测试");
        }
    }

    /**
     * AB回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method2() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        Coffee coffee = requiresNewClassB.method2();

    }

    /**
     * A提交 B回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void method3() {
        User user = new User();
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        try {
            Coffee coffee = requiresNewClassB.method3();
        } catch (Exception e) {
            System.out.println("异常测试---捕获");
        }
    }
}
