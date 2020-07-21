package com.lsl.commons.model;

import com.alibaba.fastjson.JSON;
import com.lsl.commons.model.enums.HttpStatus;

/**
 * 统一API响应结果封装
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result setCode(HttpStatus httpStatus) {
        this.code = httpStatus.code();
        return this;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(Class<T> cls) {
        T t = null;
        try {
            t = (T)data;
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return t;
    }
}
