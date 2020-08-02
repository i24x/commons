package com.lsl.common.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * 对象代理 基于反射工作 性能较低 缓存
 */
public class JdkProxy {

    static class OrderServiceHandler implements InvocationHandler {

        Object targetObject;

        public OrderServiceHandler(Object targetObject) {
            this.targetObject = targetObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(String.format("Method Name [%1$s]", method.getName()));
            if (method.getName().equals("addOrder")) {
                System.out.println("订单审核");
                Object invoke = method.invoke(targetObject, args);
                System.out.println("订单日志记录");
                return invoke;
            } else {
                return method.invoke(proxy, args);
            }
        }
    }

    public static void main(String[] args) {
        // sun.misc.ProxyGenerator.generateProxyClass(java.lang.String, java.lang.Class<?>[], int)
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        OrderService orderService = new OrderServiceImpl();
        orderService = (OrderService)Proxy.newProxyInstance(orderService.getClass().getClassLoader(),
            orderService.getClass().getInterfaces(), new OrderServiceHandler(orderService));
        orderService.addOrder(UUID.randomUUID().toString());

        // https://www.cnblogs.com/MOBIN/p/5597215.html
        // 1.通过实现InvocationHandler接口来自定义自己的InvocationHandler;
        //
        // 2.通过Proxy.getProxyClass获得动态代理类 ProxyClassFactory ProxyGenerator.generateProxyClass
        //
        // 3.通过反射机制获得代理类的构造方法，方法签名为getConstructor(InvocationHandler.class)
        //
        // 4.通过构造函数获得代理对象并将自定义的InvocationHandler实例对象传为参数传入
        //
        // 5.通过代理对象调用目标方法

        // public final void addOrder(String var1) throws {
        // try {
        // super.h.invoke(this, m4, new Object[]{var1});
        // } catch (RuntimeException | Error var3) {
        // throw var3;
        // } catch (Throwable var4) {
        // throw new UndeclaredThrowableException(var4);
        // }
        // }

        // 如何生成字节码文件 ProxyGenerator.generateClassFile 返回字节数组
        // byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
        // proxyName, interfaces, accessFlags);
        // try {
        // return defineClass0(loader, proxyName,
        // proxyClassFile, 0, proxyClassFile.length);
        // } catch (ClassFormatError e) {
        // /*
        // * A ClassFormatError here means that (barring bugs in the
        // * proxy class generation code) there was some other
        // * invalid aspect of the arguments supplied to the proxy
        // * class creation (such as virtual machine limitations
        // * exceeded).
        // */
        // throw new IllegalArgumentException(e.toString());
        // }

    }
}
