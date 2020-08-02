package com.lsl.common.exception;

import java.util.ArrayList;
import java.util.List;

public class ExceptionFactoryRegister {

    private static List<BaseException> exceptions = new ArrayList<>();

    static {
        exceptions.add(new BizException());
        exceptions.add(new BizRuntimeException());
        exceptions.add(new SystemException());
        exceptions.add(new SystemRuntimeException());
    }

    public static BaseException getException(String code) {
        for (BaseException exception : exceptions) {
            if (code != null && code.equals(exception.getCode())) {
                return exception;
            }
        }
        return null;
    }

}
