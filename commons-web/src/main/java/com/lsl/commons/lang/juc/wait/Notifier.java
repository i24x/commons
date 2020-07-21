package com.lsl.commons.lang.juc.wait;

import java.util.ArrayList;
import java.util.List;

public class Notifier {

    @SuppressWarnings("rawtypes")
    private volatile static List container = new ArrayList();

    @SuppressWarnings("unchecked")
    public void add() {
        container.add("Notifier");
    }

    public int size() {
        return container.size();
    }

    public static void main(String[] args) {
        final Notifier con = new Notifier();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        con.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {// 监听
                    if (con.size() == 5) {
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
