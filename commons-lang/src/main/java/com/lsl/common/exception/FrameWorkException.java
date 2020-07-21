package com.lsl.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class FrameWorkException extends Exception implements BaseException {

    private String code = "00003";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FrameWorkException() {
        super();
    }

    public FrameWorkException(String message) {
        super(message);
    }

    public FrameWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameWorkException(Throwable cause) {
        super(cause);
    }

    protected FrameWorkException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }
}
