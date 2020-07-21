package com.lsl.common.juc.synchronizeds;

/**
 * synchronized的重入 一个线程获取对象锁，再次请求该对象可以获得该对象的锁
 */
public class SynchronizedReenterExample {

    public synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    public synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SynchronizedReenterExample sd = new SynchronizedReenterExample();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        });
        t1.start();
    }
}
