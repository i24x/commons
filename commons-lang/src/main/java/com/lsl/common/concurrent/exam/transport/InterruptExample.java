package com.lsl.common.concurrent.exam.transport;

public class InterruptExample {
    public static void main(String[] args) {
        Runnable r = new InterruptedRunnable();
        Thread th1 = new Thread(r);
        th1.start();
        th1.interrupt();
    }

    static class InterruptedRunnable implements Runnable {
        public void run() {
            int i = 0;
            A:
            while (true) {
                if (i > 10) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // break A;
                }
                System.out.println("isInterrupted：" + Thread.currentThread().isInterrupted());
                i++;
            }
        }
    }
}
