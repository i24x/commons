package com.lsl.dsp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理借口
 * 
 * @author: CaoYeung
 * @time: 下午9:24:48
 * @param <T>
 */
public class DynamicInvocetionHander implements InvocationHandler {
    // private T subject;
    public DynamicInvocetionHander() {
        // this.subject = concreteSubject;
    }

    @Override
    public Object invoke(Object proxy, Method md, Object[] args) throws Throwable {
        System.out.println("生成代理接口：" + proxy.getClass().getName());
        System.out.println("代理接口方法：" + md.getName() + ",参数：" + args.toString());
        Object r = null;
        // r = md.invoke(this.subject, args);
        return r;
    }
}
