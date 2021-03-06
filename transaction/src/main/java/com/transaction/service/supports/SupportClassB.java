package com.transaction.service.supports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.model.Coffee;
import com.transaction.repository.CoffeeRepository;

/**
 * @description: 一定要写注释啊
 * @date: 2019-06-24 12:42
 * @author: 十一
 */
@Service
public class SupportClassB {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public Coffee method1() {
        Coffee coffee = new Coffee();
        coffee.setName("卡布其诺");
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        if (coffee.getId() > 0) {
            throw new RuntimeException("测试 propagation = Propagation.SUPPORTS ");
        }
        return coffee;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public Coffee method2() {
        Coffee coffee = new Coffee();
        coffee.setName("卡布其诺");
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        return coffee;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
    public Coffee method3() {
        Coffee coffee = new Coffee();
        coffee.setName("卡布其诺");
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        if (coffee.getId() > 0) {
            throw new RuntimeException("测试 propagation = Propagation.SUPPORTS ");
        }
        return coffee;
    }

}
