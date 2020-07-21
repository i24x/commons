package com.transaction.service.nested;

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
public class NestedClassB {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Coffee method1() {
        Coffee coffee = new Coffee();
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        if (coffee.getId() > 0) {
            throw new RuntimeException("测试 propagation = Propagation.REQUIRED ");
        }
        return coffee;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public Coffee method2() {
        Coffee coffee = new Coffee();
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        return coffee;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public Coffee method3() {
        Coffee coffee = new Coffee();
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        return coffee;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public Coffee method4() {
        Coffee coffee = new Coffee();
        coffee.setName("卡布其诺");
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        if (coffee.getId() > 1) {
            throw new RuntimeException("测试异常");
        }
        return coffee;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = RuntimeException.class)
    public Coffee method5() {
        Coffee coffee = new Coffee();
        coffee.setPrice(38.8);
        coffee = coffeeRepository.save(coffee);
        System.out.println("<======================  coffee: " + coffee.toString());
        if (coffee.getId() > 1) {
            throw new RuntimeException(
                "Propagation.NESTED 嵌套事物需要外层方法捕捉异常，否则 JpaDialect does not support savepoints - check your JPA provider's capabilities都回滚");
        }
        return coffee;
    }

}
