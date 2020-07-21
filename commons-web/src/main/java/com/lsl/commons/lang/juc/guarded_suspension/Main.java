package com.lsl.commons.lang.juc.guarded_suspension;

public class Main {

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "yuwei", 3125467L).start();
        new ServerThread(requestQueue, "xiaoxue", 4265879L).start();
    }

}