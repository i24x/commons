package com.lsl.common.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHome {

    public static Logger getInstance() {
        return Logger.getGlobal();
    }

    public static void info(String msg) {
        getInstance().log(Level.INFO, msg);
    }

    public static void warn(String msg) {
        getInstance().log(Level.WARNING, msg);
    }

    public static void warn(String msg, Throwable e) {
        getInstance().log(Level.WARNING, msg, e);
    }

    public static void error(String msg) {
        getInstance().log(Level.INFO, msg);
    }

    public static void error(String msg, Throwable e) {
        getInstance().log(Level.INFO, msg, e);
    }

}
