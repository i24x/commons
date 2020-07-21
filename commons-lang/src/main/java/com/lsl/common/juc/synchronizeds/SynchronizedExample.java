package com.lsl.common.juc.synchronizeds;

/**
 * 使用synchronized代码块加锁,比较灵活 this synchronized锁 synchronized static class锁 对象锁
 */
public class SynchronizedExample {

    // public void method0(){
    // synchronized (this) { //对象锁-this
    // try {
    // System.out.println("do method0..");
    // Thread.sleep(3000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    public synchronized void method0() {
        try {
            System.out.println("do method0..");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method1() {
        synchronized (this) { // 对象锁-this
            try {
                System.out.println("do method1..");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {// 类锁
        synchronized (SynchronizedExample.class) {
            try {
                System.out.println("do method2.. locks class");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void method2_2() {// 类锁
        try {
            System.out.println("do method2_2() .. locks synchronized static method");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Object lock = new Object();

    public void method3() { // 任何对象锁 locks
        synchronized (lock) {
            try {
                System.out.println("do method3..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method4() { // 任何对象锁 locks
        synchronized (lock) {
            try {
                System.out.println("do method4..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("method0 method1 synchronized");
        System.out.println("method3 method4 synchronized");
        final SynchronizedExample objLock = new SynchronizedExample();
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method0();
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method1();
            }
        });
        t0.start();
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2();
            }
        });

        Thread t2_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method2_2();
            }
        });
        // t2_2.start();
        // t2.start();

        t2_2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method3();
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                objLock.method4();
            }
        });

        // t3.start();
        // t4.start();

    }

}
