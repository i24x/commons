package com.transaction;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAspectJAutoProxy(exposeProxy = true) // AopContext.currentProxy()
public class SpringTransactionApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // userService.insertTow();
        // coffeeService.insert();
    }
}
