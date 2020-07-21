package com.lsl.commons.lang.juc.volatilenoatomic;

/**
 * VM args : -server，while循环内不要有标准输出 现象去掉volatile会出现死循环
 */
public class Volatile implements Runnable {
    private /*volatile*/ boolean stoped = false;
    private int i = 0;

    public void stopProgram() {
        this.stoped = true;
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile v = new Volatile();
        Thread t = new Thread(v, "volatile");
        t.start();
        Thread.sleep(10);
        v.stopProgram();
        System.out.println(v.i);
    }

    @Override
    public void run() {
        while (!stoped) {
            i++;
        }
        System.out.println("程序被终止......");
    }
}
