package com.lsl.commons.lang.juc.volatilenoatomic;

public class RunThread extends Thread {
    // 可见性
    private /*volatile*/ boolean isRunning = true;

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("线程開始............");
        System.out.println(isRunning);
        while (isRunning == true) {
            isRunning = false;
            System.out.println(isRunning);
        }
        System.out.println("线程停止............");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        // Thread.sleep(1000);
        // rt.setRunning(false);
        System.out.println("isRunning的值已经被设置了false");
    }

}
