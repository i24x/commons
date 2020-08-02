package com.lsl.common.jvm.bitclass;

import com.lsl.common.concurrent.cas.FinallyExample;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.commons.lang3.reflect.MethodUtils;

public class BtclUtils {
    public static void main(String[] args) throws ClassNotFoundException {
        // JavaClass clazz = Repository.lookupClass("java.lang.String");
        // System.out.println(clazz);
        // printCode(clazz.getMethods());

        // System.out.println(getCode(User.class,"sayHello"));
        System.out.println(getCode(FinallyExample.class, "fin"));

    }

    public static void printCode(Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
            Code code = methods[i].getCode();
            if (code != null)
                System.out.println(code);
        }
    }

    public static Code getCode(Class cls, String methodName) {
        JavaClass jClass;
        try {
            jClass = Repository.lookupClass(cls.getName());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        }
        java.lang.reflect.Method sayHello = MethodUtils.getMatchingAccessibleMethod(cls, methodName);
        return jClass.getMethod(sayHello).getCode();
    }
}
