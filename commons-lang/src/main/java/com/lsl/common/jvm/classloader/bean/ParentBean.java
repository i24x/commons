package com.lsl.common.jvm.classloader.bean;

import java.lang.reflect.Method;

public abstract class ParentBean implements IBean{

    public static int count = 1;

    static {
        System.out.println((count++) + ":" + "ParentBean static");
    }

    {
        System.out.println((count++) + ":" + "ParentBean{}1");
    }

    public ParentBean() {
        System.out.println((count++) + ":" + "ParentBean()");
    }

    {
        System.out.println((count++) + ":" + "ParentBean{}2");
    }

    public static String getSimpleName() {
        return null;
    }

    public static void getStatic(){

    }
}
