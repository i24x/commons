package com.lsl.common.vm.reflect;

import com.lsl.common.util.LogHome;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        // 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
        Method method = declaredMethod(object, methodName, parameterTypes);

        // 抑制Java对方法进行检查,主要是针对私有方法而言
        method.setAccessible(true);

        try {
            if (null != method) {
                // 调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException e) {
            LogHome.warn("调用方法失败");
        } catch (IllegalAccessException e) {
            LogHome.warn("调用方法失败");
        } catch (InvocationTargetException e) {
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
        Field field = null;

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
        // 根据 对象和属性名通过反射 调用上面的方法获取 Field对象
        Field field = getDeclaredField(object, fieldName);
        // 抑制Java对其的检查
        field.setAccessible(true);
        try {
            // 将 object 中 field 所代表的值 设置为 value
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
}
