package com.lsl.common.juc.pool;

import com.lsl.common.util.ThreadUtils;

import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {

        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();// 抛出错误

        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();// 阻塞主线程

        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();// 丢弃最老的尝试重新入队

        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();// 直接丢弃

        RecordLogRejectedExecutionHandler recordLogRejectedExecutionHandler = new RecordLogRejectedExecutionHandler();

        ThreadPoolExecutor executor = new DefaultThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10), discardPolicy);

        for (int i = 0; i < 100; i++) {
            executor.execute(new Task(i));
        }
        ThreadUtils.block(60, TimeUnit.SECONDS);
        executor.shutdown();

        // Executors.cache
    }

    public static class Task implements Runnable {
        public Task(int taskId) {
            this.taskId = taskId;
        }

        public Task() {}

        private int taskId;

        @Override
        public void run() {
            System.out
                .println(String.format("当前线程【%1$s】运行任务ID【%3$s】【%2$s】秒", ThreadUtils.currentThreadName(), 2, taskId));
            ThreadUtils.block(2, TimeUnit.SECONDS);
            if (taskId < 3) {
                ThreadUtils.print("任务运行失败");
                throw new RuntimeException(ThreadUtils.currentThreadName());
            }
        }
    }

}

class RecordLogRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        int size = executor.getQueue().size();
        BlockingQueue<Runnable> queue = executor.getQueue();
        try {
            boolean offer = queue.offer(r, 2, TimeUnit.SECONDS);
            System.out.println("队列已满：" + size + "阻塞2秒尝试添加任务：" + offer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
