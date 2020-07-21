package com.lsl.common.juc.example.request;

import com.lsl.common.util.ThreadUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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
            ThreadUtils.print("RequestNo." + i);
            requestQueue.putRequest(request);
            ThreadUtils.block(1, TimeUnit.SECONDS);
        }
    }
}