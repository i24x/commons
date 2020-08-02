package com.lsl.common.concurrent.dsp.request;

import java.util.Random;

public class ServerThread extends Thread {
    private Random random;

    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = requestQueue.getRequst();
            System.out.println(Thread.currentThread().getName() + " handles " + request);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}