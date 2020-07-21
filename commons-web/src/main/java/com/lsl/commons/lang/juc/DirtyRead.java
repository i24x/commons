package com.lsl.commons.lang.juc;

/**
 * 业务整体需要使用完整的synchronized 保持业务的原子性 脏读例子
 */
public class DirtyRead {
    private String username = "admin";
    private String password = "admin123";

    public synchronized void setValue(String username, String password) {
        this.username = username;
        // ThreadUtil.exec("执行业务setValue", 2);
        this.password = password;
        // ThreadUtil.info("+synchronized setValue:username = "+ this.username
        // + " , password = " + this.password);
    }

    public /*synchronized*/ void getValue() {
        // ThreadUtil.info("+synchronized getValue:username = " + this.username
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
        // ThreadUtil.waitSeconds(1);
        dr.getValue();
    }
}
