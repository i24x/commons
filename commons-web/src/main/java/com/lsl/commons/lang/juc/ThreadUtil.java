package com.lsl.commons.lang.juc;

import org.apache.curator.utils.ThreadUtils;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtil {

    /**
     * 打印线程信息
     * 
     * @param msg
     */
    public static void print(String msg) {
        String threadInfo = "[线程号:" + currentThreadID() + ",线程名称:" + currentThreadName() + "]-";
        msg = threadInfo + msg;
        System.out.println(msg);
    }

    /**
     * 当前线程睡眠 n秒
     * 
     * @param n
     * @param timeUnit
     */
    public static void sleep(int n, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取线程ID
     * 
     * @return
     */
    public static long currentThreadID() {
        return Thread.currentThread().getId();
    }

    /**
     * 获取线程名称
     * 
     * @return
     */
    public static String currentThreadName() {
        return Thread.currentThread().getName();
    }

    static volatile boolean isOuShu = true;

    public static void main(String[] args) {
        // Map<String,String> map = System.getenv();
        // map.forEach((k,v)->{
        // System.out.println(k+":"+v);
        // });
        // System.out.println("===============================");
        // Properties properties = System.getProperties();
        // properties.forEach((k,v)->{
        // System.out.println(k+":"+v);
        // });

    }
}
