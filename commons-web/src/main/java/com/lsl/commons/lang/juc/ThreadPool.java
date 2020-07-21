package com.lsl.commons.lang.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lsl.commons.lang.juc.task.ThreadPoolCallable;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.submit(new ThreadPoolCallable(10, false));
        // threadPool.submit(new ThreadPoolCallable(10,true));
        threadPool.submit(new ThreadPoolCallable(10, true));
        threadPool.shutdown();
    }
}
