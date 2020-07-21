package com.lsl.common.juc.locks;

public class ThreadInterrupt {
    public static void main(String[] args) {
        Runnable r = new InterruptedRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        th1.interrupt();
    }
}
