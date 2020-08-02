package com.lsl.common.concurrent.dsp.request;

import java.util.LinkedList;

/**
 * wait()+notifyAll()
 */
public class RequestQueue {
    @SuppressWarnings("rawtypes")
    private final LinkedList queue = new LinkedList();

    public synchronized Request getRequst() {
        while (queue.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        return (Request)queue.removeFirst();
    }

    @SuppressWarnings("unchecked")
    public synchronized void putRequest(Request requst) {
        queue.add(requst);
        notifyAll();
    }
}