package com.lsl.common.jvm.classloader.bean;

/**
 * <p>
 * 描述:
 * </p>
 *
 * @author yangcao
 * @version v1.0
 * @date 2020/8/1 0:02
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        // 常量和静态字段访问不触发类加载
        // System.out.println(ParentBean.STATIC);
        // System.out.println(ParentBean.CONST);

        // 静态调用触发类加载 触发该类及父类类加载并不触发构造代码块执行
        // ParentBean.getStatic();

        // 静态字段初始化加上静态代码块按顺序执行
        // HelloBean helloBean = new HelloBean();

        // 针对构造函数以及代码块先执行父类(父类中按顺序执行构造代码块在执行构造函数)在执行子类
        System.out.println(new ChildrenBean());

    }
}
