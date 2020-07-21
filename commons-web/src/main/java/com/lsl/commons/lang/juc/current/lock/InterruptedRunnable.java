package com.lsl.commons.lang.juc.current.lock;

class InterruptedRunnable implements Runnable {
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