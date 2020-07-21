package com.lsl.commons.lang.utils.lang;

import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public static void sleep(TimeUnit unit, int t) {
        try {
            unit.sleep(t);
        } catch (InterruptedException e) {
            System.err.printf("线程被中断");
        }
    }
}
