package com.lsl.common.concurrent.exam.pool;

import com.lsl.common.util.ThreadUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadPoolExecutor extends ThreadPoolExecutor {

    private AtomicInteger remainingTask = new AtomicInteger(0);
    private AtomicInteger totalTask = new AtomicInteger(0);

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public DefaultThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {

        super.beforeExecute(t, r);

        System.out.println(String.format(
            "[" + ThreadUtils.currentThreadName()
                + "]=================>>> now : %d tasks submit, %d tasks running, %d tasks still in queue",
            totalTask.incrementAndGet(), remainingTask.incrementAndGet(), getQueueSize()));

    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        super.afterExecute(r, t);

        System.out.println(String.format(
            "[" + ThreadUtils.currentThreadName()
                + "]=================>>> afterExecute : %d tasks submit, %d tasks running, %d tasks still in queue",
            totalTask.get(), remainingTask.decrementAndGet(), getQueueSize()));

    }

    public int getQueueSize() {
        return getQueue() == null ? 0 : getQueue().size();
    }

    public int getThreadAlive() {
        return remainingTask.get();
    }

}
