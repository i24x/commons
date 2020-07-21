package com.lsl.common.jcf;

import java.math.BigInteger;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DelayedWorkQueueExample {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(1);
        final BigInteger i = new BigInteger("0");
        Runnable task = () -> {
            System.out.println(String.format("i=[%1$s]", i.add(new BigInteger("1"))));
        };
        pool.schedule(task, 1, TimeUnit.SECONDS);

        pool.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
    }
}
