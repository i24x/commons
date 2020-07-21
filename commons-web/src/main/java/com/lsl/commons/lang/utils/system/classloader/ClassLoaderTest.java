package com.lsl.commons.lang.utils.system.classloader;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader l1 = new AppClassLoader();
        Class<?> c1 = l1.loadClass("com.lsl.commons.model.Email");
        System.out.println(c1.hashCode());
        // Email
        Object obj = c1.newInstance();
        System.out.println(obj);
        l1 = null;
        c1 = null;
        // obj = null;
        System.gc();
        ClassLoader l2 = new AppClassLoader();
        Class<?> c2 = l2.loadClass("com.lsl.commons.model.Email");
        System.out.println(c2.hashCode());

        Class<?> c3 =
            Class.forName("com.lsl.commons.model.Email", true, AppClassLoader.class.getClassLoader()/*.getParent()*/);
    }
}
