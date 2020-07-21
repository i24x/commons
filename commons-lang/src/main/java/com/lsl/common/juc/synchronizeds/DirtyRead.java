package com.lsl.common.juc.synchronizeds;

/**
 * 业务整体需要使用完整的synchronized 保持业务的原子性 脏读例子
 */
public class DirtyRead {
    private String username = "admin";
    private String password = "admin123";

    public synchronized void setValue(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public /*synchronized*/ void getValue() {
        // ThreadUtil.1并发包("+synchronized getValue:username = " + this.username
        // + ", password = " + this.password);
    }

    public static void main(String[] args) throws Exception {

        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("root", "root456");
            }
        });
        t1.start();
        dr.getValue();
    }
}
