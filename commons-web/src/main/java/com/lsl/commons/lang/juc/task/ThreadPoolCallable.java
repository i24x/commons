package com.lsl.commons.lang.juc.task;

import java.util.concurrent.Callable;

public class ThreadPoolCallable implements Callable<Integer> {
    public Integer length;
    public Boolean f;

    public ThreadPoolCallable(Integer num, Boolean b) {
        this.length = num;
        this.f = b;
    }

    @SuppressWarnings("static-access")
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < length; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if (i == 4 && f == true) {
                // Thread.currentThread().wait();
                Thread.currentThread().yield();
            }
        }
        return length;
    }

}
