package com.lsl.common.juc.wait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁
 * 
 * @author alienware
 * 
 */
@SuppressWarnings("rawtypes")
public class CountDownLatchExample {
    private volatile static List list = new ArrayList();

    @SuppressWarnings("unchecked")
    public void add() {
        list.add("CountDownLatchExample");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final CountDownLatchExample list2 = new CountDownLatchExample();
        final CountDownLatch countDownLatch = new CountDownLatch(1);// 触发一次

        // CountDownLatch --》 await --》CountDown
        // syn+locks+wait+notify
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list2.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素........");
                        Thread.sleep(500);
                        if (list2.size() == 5) {
                            System.out.println("已经发出通知........");
                            countDownLatch.countDown();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "A");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                if (list2.size() != 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止...........");
            }
        }, "B");

        t2.start();
        t1.start();

    }

}
