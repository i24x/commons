package com.lsl.commons.lang.juc;

import java.util.concurrent.TimeUnit;

import com.lsl.commons.lang.juc.task.OrderTask;

public class OrderThread {
    public static void main(String[] args) {
        Thread t = new Thread(new OrderTask(), "OrderTask");
        // t.setPriority(Thread.NORM_PRIORITY);
        t.setDaemon(true);// 守护线程
        t.start();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(0);
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if (i == 4) {
                    // t.interrupt();
                    // t.stop(); //Thread.State.TERMINATED==>结束
                    // System.exit(0);//退出JAVA虚拟机
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*new Thread() {
        	public void run() {
        		for (int x = 0; x < 40; x++) {
        			System.out.println(Thread.currentThread().getName()
        					+ "...X...." + x);
        		}
        	}
        }.start();
        
        new Thread(new Runnable() {
        	public void run() {
        		for (int x = 0; x < 40; x++) {
        			System.out.println(Thread.currentThread().getName()
        					+ "...Y...." + x);
        		}
        	}
        }).start();*/
    }
}
