package com.lsl.common.concurrent.exam.transport;

import java.util.ArrayList;
import java.util.List;

/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁 线程通信不实时
 */
public class WaitExample {

    private volatile static List container = new ArrayList();

    public void add() {
        container.add("element");
    }

    public int size() {
        return container.size();
    }

    public static void main(String[] args) {

        final WaitExample list2 = new WaitExample();

        // 1 实例化出来一个 locks
        // 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            list2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                            Thread.sleep(500);
                            if (list2.size() == 5) {
                                System.out.println("已经发出通知...........");
                                lock.notify();// 不释放锁
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (list2.size() != 5) {
                        try {
                            System.out.println("t2进入...");
                            lock.wait();// 释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知、处理通知、线程停止..");
                }
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
