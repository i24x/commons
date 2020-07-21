package com.lsl.commons.config.boot.support;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.lsl.commons.model.Email;

public class BeanWrapperTester {
    public static void main(String[] args) {
        Email email = new Email();
        // BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(email);
        BeanWrapper bw2 = new BeanWrapperImpl(email);
        bw2.setPropertyValue("address", "chengdu");
        System.out.println(email);
        String[] arrs = new String[10];
        System.out.println(arrs.getClass().isArray());
        postParam(1);
    }

    /**
     * 基本数据类型自动拆装箱
     * 
     * @param obj
     */
    public static void postParam(Object obj) {
        System.out.println(obj.getClass());
    }
}
