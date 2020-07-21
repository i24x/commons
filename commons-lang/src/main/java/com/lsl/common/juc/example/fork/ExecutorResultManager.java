package com.lsl.common.juc.example.fork;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorResultManager {

    public static void main(String[] args) {
        // 队列
        final BlockingQueue<Future<String>> futures = new LinkedBlockingQueue<Future<String>>();
        // 生产者
        new Thread() {
            @Override
            public void run() {
                ExecutorService pool = Executors.newFixedThreadPool(2);
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    Future<String> submit = pool.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                    try {
                        futures.put(submit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("isTerminated:" + pool.isTerminated());
                pool.shutdown();
                System.out.println("isTerminated:" + pool.isTerminated());

            }
        }.start();

        // 消费者
        new Thread() {
            @Override
            public void run() {
                System.out.println("FUTURES.ISEMPTY():" + futures.isEmpty());
                boolean isRunning = true;
                while (isRunning) {
                    if (!futures.isEmpty()) {
                        isRunning = false;
                    }
                }
                isRunning = true;
                while (isRunning) {

                    if (!futures.isEmpty()) {
                        try {
                            Future<String> f = futures.take();
                            System.out.println(f.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        isRunning = false;
                    }
                }
            }
        }.start();

    }

}
