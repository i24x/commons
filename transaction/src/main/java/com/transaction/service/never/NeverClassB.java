package com.transaction.service.never;

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
public class NeverClassB {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Transactional(propagation = Propagation.NEVER, rollbackFor = RuntimeException.class)
    public Coffee method1() {
        Coffee coffee = new Coffee();
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        if (coffee.getId() > 1) {
            throw new RuntimeException("NeverClassB 必须在非事物环境运行");
        }
        return coffee;
    }

}
