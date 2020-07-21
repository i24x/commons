package com.lsl.commons.config.boot.support;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class BeanUtilTester {
    public static void main(String[] args) {
        ClassUtils.getPackageName(Object.class);
        BeanUtils.findMethod(Object.class, "toString");
        ObjectUtils.getIdentityHexString(Object.class);
    }
}
