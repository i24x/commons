package com.lsl.commons.lang.juc.changelock;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 */
public class ConstStringLock {
    private String s1 = "字符串常量";
    private String s2 = new String("字符串常量");

    public void method() {
        // new String("字符串常量")
        synchronized (new String("字符串常量")) {// s1 s2 导致同步 //new
            try {
                while (true) {
                    System.out.println("当前线程 : " + Thread.currentThread().getName() + "开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程 : " + Thread.currentThread().getName() + "结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ConstStringLock stringLock = new ConstStringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "A");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "B");

        t1.start();
        t2.start();
    }
}
