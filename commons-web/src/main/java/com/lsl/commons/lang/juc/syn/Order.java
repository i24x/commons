package com.lsl.commons.lang.juc.syn;

public class Order {
    private static int ticket = 100;

    public /*synchronized*/ static void A() {
        System.out.println("A()");
        while (Order.ticket > 0) {
            Order.sleep(10);
            System.out.println(Thread.currentThread().getId() + ":" + Order.ticket--);
        }
    }

    public /*synchronized*/ static void B() {
        System.out.println("B()");
        while (Order.ticket > 0) {
            Order.sleep(10);
            System.out.println(Thread.currentThread().getId() + ":" + Order.ticket--);
        }
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
