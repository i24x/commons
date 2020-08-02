package com.lsl.common.jvm.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.lsl.common.util.LogHome;

public class ReflectionUtils {

    private ReflectionUtils() {

    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     *
     * @param object
     *            : 子类对象
     * @param methodName
     *            : 父类中的方法名
     * @param parameterTypes
     *            : 父类中的方法参数类型
     * @return 父类中的方法对象
     */

    public static Method declaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
                LogHome.info("获取类定义方法失败");
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     *
     * @param object
     *            : 子类对象
     * @param methodName
     *            : 父类中的方法名
     * @param parameterTypes
     *            : 父类中的方法参数类型
     * @param parameters
     *            : 父类中的方法参数
     * @return 父类中方法的执行结果
     */

    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes,
        Object[] parameters) {
        Method method = declaredMethod(object, methodName, parameterTypes);
        method.setAccessible(true);
        try {
            if (null != method) {
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            LogHome.warn("调用方法失败");
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @return 父类中的属性对象
     */

    public static Field getDeclaredField(Object object, String fieldName) {

        Field field;

        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                LogHome.warn("获取定义方法失败");
            }
        }

        return null;
    }

    public static Collection<Field> getAllFields(Class<?> clazz) {
        Map<String, Field> fields = new HashMap<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                if (!fields.containsKey(name)) {
                    fields.put(name, declaredField);
                }
            }
        }
        return fields.values();
    }

    /**
     * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @param value
     *            : 将要设置的值
     */

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            LogHome.warn("设置属性值失败");
        } catch (IllegalAccessException e) {
            LogHome.warn("设置属性值失败");
        }

    }

    /**
     * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
     *
     * @param object
     *            : 子类对象
     * @param fieldName
     *            : 父类中的属性名
     * @return : 父类中的属性值
     */

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (Exception e) {
            LogHome.warn("获取属性值失败");
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = new int[6];
        arr[0] = 1;
        Class<? extends int[]> cls = arr.getClass();
        if(cls.isArray()){
            Class<?> componentType = cls.getComponentType();
            System.out.println(componentType);
            int length = Array.getLength(arr);
            for (int i = 0; i < length; i++) {
                System.out.println(Array.getInt(arr,i));
                System.out.println(Array.get(arr,i));
            }
        }
    }
}
