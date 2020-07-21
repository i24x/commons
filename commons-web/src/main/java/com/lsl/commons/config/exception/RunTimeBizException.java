package com.lsl.commons.config.exception;

/**
 * <p>
 * 描述:
 * </p>
 *
 * @author yangcao
 * @version v1.0
 * @date 2020/7/16 23:05
 */
public class RunTimeBizException extends BaseException {
    @Override
    public int getCode() {
        return 20_100;
    }
}
