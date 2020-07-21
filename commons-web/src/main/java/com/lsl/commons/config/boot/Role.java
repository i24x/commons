package com.lsl.commons.config.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class Role {
    @Value("${name:liming}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + "]";
    }

    @Bean
    @ConditionalOnMissingBean(User.class)
    public User createUser() {
        System.out.println("createUser***************");
        return new User();
    }

}
