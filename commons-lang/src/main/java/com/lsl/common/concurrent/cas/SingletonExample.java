package com.lsl.common.concurrent.cas;

public class SingletonExample {

    private volatile static SingletonExample ds;

    public static SingletonExample getDs() {
        if (ds == null) {
            try {
                // 模拟初始化对象的准备时间...
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (SingletonExample.class) {
                if (ds == null) {
                    ds = new SingletonExample();
                }
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingletonExample.getDs().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingletonExample.getDs().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingletonExample.getDs().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
