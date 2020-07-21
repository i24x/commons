package com.lsl.commons.model;

import com.lsl.commons.model.enums.HttpStatus;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result newuccessResult() {
        return new Result().setCode(HttpStatus.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result newSuccessResult(Object data) {
        return new Result().setCode(HttpStatus.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }

    public static Result newResult(int code, String msg, Object data) {
        return new Result().setCode(code).setMessage(msg).setData(data);
    }

    public static Result newFailResult(String message) {
        return new Result().setCode(HttpStatus.INTERNAL_SERVER_ERROR).setMessage(message);
    }

    public static Result newFailResult(int code, String message) {
        return new Result().setCode(code).setMessage(message);
    }
}
