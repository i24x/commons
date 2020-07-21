package com.lsl.common.bean;

import java.lang.reflect.Method;

public class ClassBeanInit extends ParentBeanX {
    static {
        System.out.println((count++) + ":" + "子类静态代码块static{}");
    }

    {
        System.out.println((count++) + ":" + "子类构造代码块{}");
    }

    public ClassBeanInit() {
        super();
        System.out.println((count++) + ":" + "子类构造函数new");
    }

    @Override
    public Method getMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Class<?> classType = object.getClass();
        Method getMethod = null;
        try {
            // Member.PUBLIC
            getMethod = classType.getMethod(methodName, parameterTypes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (getMethod == null) {
            // Member.DECLARED
            try {
                getMethod = classType.getDeclaredMethod(methodName, parameterTypes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (getMethod != null) {
            getMethod.setAccessible(true);
        } else {
            throw new RuntimeException(methodName);
        }
        return getMethod;
    }

    public void testFunction(String a, int b) {
        System.out.println("public void testFunction(String a,int b)->" + a + "," + b);
    }

    public static void main(String[] args) throws Exception {
        ClassBeanInit b = new ClassBeanInit();
        String className = getSimpleName(b);
        // System.out.println(className);
        Method method = b.getMethod(b, "testFunction", String.class, int.class);
        method.setAccessible(true);
        method.invoke(b, "SDDD", 134);
    }
}
