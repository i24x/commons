package com.lsl.commons.lang.juc.guarded_suspension;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.lsl.commons.lang.juc.ThreadUtil;

public class ClientThread extends Thread {
    @SuppressWarnings("unused")
    private Random random;

    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request = new Request("No." + i);
            ThreadUtil.print("RequestNo." + i);
            requestQueue.putRequest(request);
            ThreadUtil.sleep(1, TimeUnit.SECONDS);
        }
    }
}