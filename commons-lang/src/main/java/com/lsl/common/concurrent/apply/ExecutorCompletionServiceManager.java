package com.lsl.common.concurrent.apply;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCompletionServiceManager {
	public static void main(String[] args) {

        final ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(
                Executors.newCachedThreadPool());
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    final int index = i;
                    service.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "task done" + index;
                        }
                    });
                }
            }
        }.start();
        

        // 消费者
        new Thread() {
            @Override
            public void run() {
                try {
                    Future<String> take = service.take();
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
