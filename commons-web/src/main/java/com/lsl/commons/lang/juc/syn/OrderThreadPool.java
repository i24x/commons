package com.lsl.commons.lang.juc.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        threadPool.submit(new OrderBeforeTask());
        threadPool.submit(new OrderAfterTask());
        threadPool.submit(new OrderAfterTask());
        threadPool.shutdown();
    }
}
