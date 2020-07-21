package com.lsl.commons.lang.juc.task;

import java.util.concurrent.TimeUnit;

public class OrderTask implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(0);
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Thread.currentThread().sleep(1000);
    }

}
