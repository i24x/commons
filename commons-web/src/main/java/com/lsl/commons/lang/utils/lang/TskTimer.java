package com.lsl.commons.lang.utils.lang;

import java.util.Timer;
import java.util.TimerTask;

public class TskTimer {
    public static void main(String[] args) {
        // 每次延迟10ms，每隔1s执行一次
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 10L, 1000L);
    }
}
