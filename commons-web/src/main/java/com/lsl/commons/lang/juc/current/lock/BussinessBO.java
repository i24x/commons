package com.lsl.commons.lang.juc.current.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BussinessBO {

    private Lock lock = new ReentrantLock();

    // 业务方法
    public void doService() throws InterruptedException {
        String tName = Thread.currentThread().getName();
        lock.lockInterruptibly();// 前提当前锁被其他线程持有，先中断，获取不到锁，不执行业务->使用的时候不要捕捉
        // locks.locks();//先取锁,执行业务，后相应中断处理->Thread.sleep wait join
        try {
            System.out.println(tName + "-************获取到锁成功************");
            System.out.println(tName + "-doService执行10秒，过程阻塞.....");
            Thread.sleep(10000);
            System.out.println(tName + "-睡醒了，干活！");
            for (int i = 0; i < 5; i++) {
                System.out.println(tName + "：" + i);
            }
        } finally {
            lock.unlock();
            System.out.println(tName + "-释放了锁");
        }
    }

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }
}