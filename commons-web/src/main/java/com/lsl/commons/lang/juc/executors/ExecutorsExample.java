package com.lsl.commons.lang.juc.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {
    static class TaskRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable .......");
        }

    }

    static class TaskCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Callable .......");
            return new String("Callable.......");
        }

    }

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newSingleThreadExecutor();
        // Future<?> f1 = pool.submit(new TaskRunnable());
        // Object o1 = f1.get();
        // System.out.println(o1);

        Future<String> f2 = pool.submit(new TaskCallable());
        String o2 = f2.get();
        pool.submit(new TaskCallable());
        System.out.println(o2);
        // cache fixed single
        pool.shutdown();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(10);
        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadExecutor();
        ScheduledExecutorService pool0 = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService pool1 = Executors.newScheduledThreadPool(10);

        ThreadPoolExecutor executor =
            new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    }
}
