package com.lsl.common.juc.locks;

/**
 * 锁对象的改变问题-synchronized失效
 */
public class ChangeStringLock {
    private String lock = "lock_v1";

    private void method() {
        synchronized (lock) {
            System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
            lock = "lock_v2";
            // ThreadUtil.waitSeconds(2);
            System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {

        final ChangeStringLock changeLock = new ChangeStringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
