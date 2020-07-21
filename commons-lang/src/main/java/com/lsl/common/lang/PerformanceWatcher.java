package com.lsl.common.lang;

// import com.lsl.common.util.LogHome;

/*
 * 程序执行时间控制
 */
public class PerformanceWatcher {

    private Long startTime;

    private Long currentTime;

    private PerformanceWatcher() {}

    public static PerformanceWatcher getInstance() {
        return new PerformanceWatcher();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void restart() {
        start();
    }

    public void clear() {
        startTime = null;
        currentTime = null;
    }

    public long watch() {
        if (startTime == null) {
            startTime = System.currentTimeMillis();
        }
        currentTime = System.currentTimeMillis();
        // LogHome.info(String.format("服务执行时间[%d]毫秒",(currentTime-startTime)));
        return currentTime - startTime;
    }
}
