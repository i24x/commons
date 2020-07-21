package com.lsl.commons.lang.utils.system.bean;


public class PossibleReordering {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    // https://www.jianshu.com/p/8a58d8335270
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 由于线程先启动，下面这句话让它等一等线程. 读着可根据自己电脑的实际性能适当调整等待时间.
                    shortWait(1000);
                    a = 1;
                    x = b; // x=0|1
                    System.out.println("T1");
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;// y=1|0
                    System.out.println("T2");
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            // int bb =1233_122;
            // int bbc =0b010101;
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println("发生重排序=======" + result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
