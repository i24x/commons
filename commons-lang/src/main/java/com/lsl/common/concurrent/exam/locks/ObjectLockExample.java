package com.lsl.common.concurrent.exam.locks;

/**
 * synchronized 锁使用
 * 使用synchronized(obj)代码块加锁,比较灵活
 * 方法锁   synchronized(this)
 * static synchronized 方法锁   class锁 this.getClass()
 * 对象锁
 */
public class ObjectLockExample {
	
//	public void method0(){
//		synchronized (this) {	//对象锁-this
//			try {
//				System.out.println("do method0..");
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public  void method0_0(){
			try {
				System.out.println("do method0_0..");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public synchronized void method0_1(){
		try {
			System.out.println("do method0_1..");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
	
	
	public void method0_2(){
		synchronized (this) {	//对象锁-this
			try {
				System.out.println("do method0_2..");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void method2_0(){//类锁
		synchronized (ObjectLockExample.class) {
			try {
				System.out.println("do method2_0.. lock class");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized static void method2_1(){//当前类.class锁
			try {
				System.out.println("do method2_1() .. lock synchronized static method");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	private Object lock = new Object();
	public void method3_0(){//任何对象锁 lock
		synchronized (lock) {
			try {
				System.out.println("do method3_0..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void method3_1(){//任何对象锁 lock
		synchronized (lock) {
			try {
				System.out.println("do method3_1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		final ObjectLockExample bean = new ObjectLockExample();
		/*Thread t0_0 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method0_0();
			}
		});
		Thread t0_1 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method0_1();
			}
		});
		Thread t0_2 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method0_2();
			}
		});
		
		t0_0.start();
		t0_1.start();
		t0_2.start();*/
		
		/*Thread t2_0 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method2_0();
			}
		});
		
		Thread t2_1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ObjectLock.method2_1();
			}
		});
		
		t2_0.start();
		t2_1.start();*/
		
		Thread t3_0 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method3_0();
			}
		});
		Thread t3_1 = new Thread(new Runnable() {
			@Override
			public void run() {
				bean.method3_1();
			}
		});
		
		t3_0.start();
		t3_1.start();
		
	}
	
}
