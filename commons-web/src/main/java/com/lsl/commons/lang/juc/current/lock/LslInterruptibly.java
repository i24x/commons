package com.lsl.commons.lang.juc.current.lock;

/*
 * 1）如果当前线程未被中断，则获取锁。 2）如果该锁没有被另一个线程保持，则获取该锁并立即返回，将锁的保持计数设置为 1。
 * 
 * 
 * 3）如果当前线程已经保持此锁，则将保持计数加 1，并且该方法立即返回。 //重入性
 * 
 * ！ 4）如果锁被另一个线程保持，则出于线程调度目的，禁用当前线程，并且在发生以下两种情况之一以 前，该线程将一直处于休眠状态： 1）锁由当前线程获得；或者 2）其他某个线程中断当前线程。 5）如果当前线程获得该锁，则将锁保持计数设置为
 * 1。 如果当前线程： 1）在进入此方法时已经设置了该线程的中断状态；或者 2）在等待获取锁的同时被中断。 则抛出 InterruptedException，并且清除当前线程的已中断状态。
 * 6）在此实现中，因为此方法是一个显式中断点，所以要优先考虑响应中断，而不是响应锁的普通获取或 重入获取。
 */
public class LslInterruptibly {
    public static void main(String[] args) throws Exception {
        final BussinessBO bc = new BussinessBO();

        Thread t0 = new Thread() {
            @Override
            public void run() {
                try {
                    bc.doService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    bc.doService();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        String tName = Thread.currentThread().getName();
        System.out.println(tName + "-Thread-0启动，尝试获取锁");
        t0.start();
        Thread.sleep(1000);
        System.out.println(tName + "-Thread-1启动，尝试获取锁");
        t1.start();
        t1.interrupt();
        // Thread.sleep(Long.MAX_VALUE);
    }
}
