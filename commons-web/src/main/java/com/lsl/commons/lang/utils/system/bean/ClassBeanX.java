package com.lsl.commons.lang.utils.system.bean;

import java.lang.reflect.Method;

public class ClassBeanX extends ParentBeanX {
    static {
        System.out.println((count++) + ":" + "子类静态代码块");
    }

    public ClassBeanX() {
        super();
        System.out.println((count++) + ":" + "子类构造函数");
    }

    {
        System.out.println((count++) + ":" + "子类构造代码块");
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

    static int k = 10;

    static {
        System.out.println("k += 2");
        k += 2;
    }
    {
        k = k + 1;
    }

    public void testFunction(String a, int b) {
        System.out.println("public void testFunction(String a,int b)->" + a + "," + b);
    }

    public static void main(String[] args) throws Exception {
        // ClassBeanX b = new ClassBeanX();
        // String className = ParentBeanX.getSimpleName(b);
        // System.out.println(className);
        //
        // Method method = b.getMethod(b, "testFunction", String.class,int.class);
        // method.setAccessible(true);
        // method.invoke(b, "SDDD",134);
        //
        // int[] arr = new int[24];
        // System.out.println(arr[23]);
        System.out.println(ClassBeanX.k);

    }

    static {
        k = k + 3;
        System.out.println("k += 3");
    }
}
