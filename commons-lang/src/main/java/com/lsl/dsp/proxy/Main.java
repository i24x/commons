package com.lsl.dsp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        InvocationHandler handler = new DynamicInvocetionHander();
        Class<?> cls = Class.forName("com.lsl.dsp.proxy.Subject");
        Subject Subject = (Subject)Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[] {cls}, handler);
        Subject.exec("122");
    }
}
