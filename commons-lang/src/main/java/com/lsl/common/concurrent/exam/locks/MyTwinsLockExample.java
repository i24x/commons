package com.lsl.common.concurrent.exam.locks;

import com.lsl.common.util.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * { @link https://www.cnblogs.com/yougewe/articles/10054119.html }
 */
class MyTwinsLockExample implements Lock {

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -8540764104913403569L;

        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("锁资源数不能为负数~");
            }
            // 调用 AQS 设置资源总数，备用
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            // cas 获取锁
            // 由 AQS 的 acquireShared -> doAcquireShared 调用
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            // cas 释放锁
            // 由AQS releaseShared -> doReleaseShared 调用
            for (;;) {
                int current = getState();
                int newState = current + returnCount;
                if (compareAndSetState(current, newState)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    // 忽略，如要实现，直接调用 AQS
    @Override
    public boolean tryLock() {
        return false;
    }

    // 忽略，如要实现，直接调用 AQS
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    // 忽略，如要实现，直接调用 AQS
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    // 忽略，如要实现，直接调用 AQS
    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        final Lock lock = new MyTwinsLockExample();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    // 获取锁
                    lock.lock();
                    try {
                        ThreadUtils.block(100, TimeUnit.SECONDS);
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName());
                        ThreadUtils.block(100, TimeUnit.SECONDS);
                    } finally {
                        // 释放锁
                        lock.unlock();
                    }
                }
            }
        }
        // 开10个线程运行worker, 如果没有锁，应该是几乎同时很快完成
        // 但 TwinsLock 只允许同时有两个线程获得锁运行
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1s换行
        for (int i = 0; i < 10; i++) {
            ThreadUtils.block(100, TimeUnit.SECONDS);
            System.out.println();
        }
    }
}