package com.lsl.commons.config.exception;

/**
 * <p>
 * 描述:
 * </p>
 * 
 * @author yangcao
 * @version v1.0
 * @date 2020/7/16 23:00
 */
public abstract class BaseException extends RuntimeException {
    public abstract int getCode();
}
