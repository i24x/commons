package com.lsl.common.juc.example.fork;

import java.util.Random;

public class FindMaxValueJobTest {

    public static void main(String[] args) throws Exception {
        Random r = new Random();
        int[] serviceData = new int[10];
        for (int i = 0; i < 10; i++) {
            serviceData[i] = r.nextInt(888);
        }
        int v = MultithreadedMaxFinder.max(serviceData);
        System.out.println(v);
    }

}
