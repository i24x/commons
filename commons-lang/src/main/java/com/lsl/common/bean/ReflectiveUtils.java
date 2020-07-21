package com.lsl.common.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectiveUtils {

    public void test(String var1, String var2) {
        System.out.println(var1 + "," + var2);
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<ReflectiveUtils> cls = ReflectiveUtils.class;
        Method m0 = cls.getMethod("test", String.class, String.class);
        try {
            // m0= null;
            ReflectiveUtils o = new ReflectiveUtils();
            String[] parms = {"", ""};
            m0.invoke(null, parms);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
