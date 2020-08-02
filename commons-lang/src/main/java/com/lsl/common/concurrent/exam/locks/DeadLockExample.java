package com.lsl.common.concurrent.exam.locks;

/**
 * 死锁问题，在设计程序时就应该避免双方相互持有对方的锁的情况
 * @author alienware
 *
 */
public class DeadLockExample implements Runnable{

	private String tag;
	private static Object lock_a = new Object();
	private static Object lock_b = new Object();
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	@Override
	public void run() {
		if(tag.equals("a")){
			synchronized (lock_a) {
				try {
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock_b) {
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
				}
			}
		}
		if(tag.equals("b")){
			synchronized (lock_b) {
				try {
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock_a) {
					System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		DeadLockExample d1 = new DeadLockExample();
		d1.setTag("a");
		DeadLockExample d2 = new DeadLockExample();
		d2.setTag("b");
		 
		Thread t1 = new Thread(d1, "t1");
		Thread t2 = new Thread(d2, "t2");
		 
		t1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
//		System.exit(0);
	}


	
}
