package com.lsl.commons.lang.juc.syn;

public class OrderAfterTask implements Runnable {

    @Override
    public void run() {
        Order.B();
    }

}
