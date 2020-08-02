package com.lsl.common.jvm.command;

/**
 * 描述2个线程操作成员变量 同时修改两个变量， 两个线程分别用2个变量接受对方修改后变量， 出现不可能情况（0,0）说明存在指令重拍
 */
public class CommandReOrder {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;
    private static final String anc = "1222222222";

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;) {
            i++;
            x = 0;
            y = 0;

            a = 0;
            b = 0;
            Thread one = new Thread(new Runnable() {
                public void run() {
                    // 由于线程one先启动，下面这句话让它等一等线程two. 读着可根据自己电脑的实际性能适当调整等待时间.
                    shortWait(10000);
                    a = 1;
                    x = b;
                    System.out.println("A");
                }
            });

            Thread other = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                    System.out.println("B");
                }
            });
            one.start();
            other.start();
            one.join();
            other.join();
            int bb = 1233_122;
            int bbc = 0b010101;
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0) {
                System.err.println(result);
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
