package com.lsl.commons.lang.juc.current.lock;

import com.lsl.commons.lang.utils.lang.TimeUtil;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static void main(String[] args) {
        Runnable r = new InterruptedRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        th1.interrupt();

        TimeUtil.sleep(TimeUnit.HOURS, 1);

    }
}
