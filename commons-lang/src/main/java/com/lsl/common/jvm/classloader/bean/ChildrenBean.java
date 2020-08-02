package com.lsl.common.jvm.classloader.bean;

public class ChildrenBean extends ParentBean {

    static {
        System.out.println((count++) + ":" + "ChildrenBean static");
    }

    {
        System.out.println((count++) + ":" + "ChildrenBean {}1");
    }

    public ChildrenBean() {
//        super();
        System.out.println((count++) + ":" + "ChildrenBean()");
    }
    {
        System.out.println((count++) + ":" + "ChildrenBean {}2");
    }

    public static String getSimpleName() {
        return null;
    }

    //主方法触发类加载
    public static void main(String[] args) {

    }

}
