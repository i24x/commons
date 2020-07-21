package com.lsl.commons.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    Logger logger = LoggerFactory.getLogger("LogUtil");
    private static LogUtil home = new LogUtil();

    private LogUtil() {}

    public static LogUtil getInstance() {
        return home;
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(String msg, Throwable t) {
        logger.info(msg, t);
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void debug(String msg, Throwable t) {
        logger.debug(msg, t);
    }

    public void error(String msg) {
        logger.info(msg);
    }

    public void error(String msg, Throwable t) {
        logger.info(msg, t);
    }

}
