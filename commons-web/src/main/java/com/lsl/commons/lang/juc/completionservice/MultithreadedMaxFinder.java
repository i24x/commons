package com.lsl.commons.lang.juc.completionservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 任務分割 Demo
 * 
 * @author lsl
 */
public class MultithreadedMaxFinder {
    public static int max(int[] data) throws InterruptedException, ExecutionException {
        if (data.length == 1) {
            return data[0];
        } else if (data.length == 0) {
            throw new IllegalArgumentException();
        }
        // split the job into 2 pieces
        MaxValueJobCallable task1 = new MaxValueJobCallable(data, 0, data.length / 2);
        MaxValueJobCallable task2 = new MaxValueJobCallable(data, data.length / 2, data.length);
        // spawn 2 threads
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);
        int v = Math.max(future1.get(), future2.get());
        service.shutdown();
        return v;
    }
}
