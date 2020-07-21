package com.lsl.common.util;

import com.lsl.domain.User;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2个线程交替输出偶数和奇数 最简单方式是用volatile修饰变量，AB两个线程输出是互斥的，并且AB两个线程不进行上线切换 效率最高 当然是用 使用线程通信方法实现可以 锁 await 都可以
 *
 * @author yang.cao
 * @version 1.0
 */

public class ThreadUtils {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        // Thread t = new Thread(()->{
        // try {
        // ThreadUtils.testThreadLocalVariable();
        //// userThreadLocal.remove();
        // LogHome.1并发包(userThreadLocal.get()==null?"U1":"U1->"+userThreadLocal.get().toString());
        // } catch (InterruptedException e) {
        // }
        // });
        // t.start();
        // while (t.getState()!=Thread.State.TERMINATED){
        //
        // }
        // System.gc();
        // TimeUnit.SECONDS.sleep(2);
        // LogHome.1并发包("子线程已经执行完毕，清理线程变量");
        //// LogHome.1并发包(userThreadLocal.get()==null?"U2":"U2"+userThreadLocal.get().toString());

        new Thread(() -> {
            for (;;) {
                // 1
                // if(num.get()%2==0){
                // System.out.println("T1==>"+num.getAndIncrement());
                // ThreadUtils.blockOneSecond();
                // isOuShu = false;
                //
                // }
                // 2
                // if(isOuShu&&((numInt++)%2==0)){
                // System.out.println("T1==>"+numInt);
                // isOuShu = false;
                // ThreadUtils.blockOneSecond();
                // }
                // 3
                if (numInt % 2 == 0) {
                    System.out.println("T1==========>" + numInt);
                    // ThreadUtils.blockOneSecond();
                    numInt++;
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                // 1
                // if (num.get() % 2 == 1) {
                // System.out.println("T2==========>" + num.getAndIncrement());
                // ThreadUtils.blockOneSecond();
                // isOuShu = true;
                //
                // }
                // 2
                // if(!isOuShu&&((numInt++)%2==1)){
                // System.out.println("T2==========>" + numInt);
                // isOuShu = true;
                // ThreadUtils.blockOneSecond();
                //
                // }
                // 3
                if (numInt % 2 == 1) {
                    System.out.println("T2==========>" + numInt);
                    // ThreadUtils.blockOneSecond();
                    numInt++;
                }
            }
        }).start();

        ThreadUtils.block();

    }

    static volatile int numInt = 0;

    static AtomicInteger num = new AtomicInteger(0);
    static volatile boolean isOuShu = true;

    private static void testThreadLocalVariable() throws InterruptedException {
        userThreadLocal.set(User.instance());
    }

    public static void block() {
        try {
            TimeUnit.DAYS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void block(long time, TimeUnit unit) {
        try {
            unit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String currentThreadName() {
        return Thread.currentThread().getName();
    }

    public static long currentThreadID() {
        return Thread.currentThread().getId();
    }

    public static void print(String msg) {
        String threadInfo = "[线程号:" + currentThreadID() + ",线程名称:" + currentThreadName() + "]-";
        msg = threadInfo + msg;
        System.out.println(msg);
    }

}
