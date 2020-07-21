package com.lsl.commons.lang.utils.system.bean;

import java.lang.reflect.Method;

public abstract class ParentBeanX {
    public static int count = 1;
    static {
        System.out.println("====>1.先执行父子静态代码块 2.执行父构造代码块，构造函数 3.执行子类构造代码块，构造函数 ");
        System.out.println((count++) + ":" + "父类静态代码块");
    }

    public ParentBeanX() {
        System.out.println((count++) + ":" + "父类构造函数");
    }

    {
        System.out.println((count++) + ":" + "父类构造代码块");
    }

    public abstract Method getMethod(Object object, String methodName, Class<?>... parameterType);

    public static String getSimpleName(Object obj) {
        Class<? extends Object> classType = obj.getClass();
        return classType.getSimpleName();
    }
}
