package com.lsl.commons.lang.juc.syn;

public class OrderBeforeTask implements Runnable {

    @Override
    public void run() {
        Order.A();
    }

}
