package com.lsl.common.juc.example.fork;

import java.util.concurrent.Callable;

public class ServiceJobCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        try {// 模拟任务，执行了500毫秒；
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "TaskCallsble 執行結果";
    }
}
