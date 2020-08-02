package com.lsl.common.concurrent.exam.multilock;



/**
 * synchronized异常
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(i<10){
			try {
				i++;
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 5){
					//Integer.parseInt("a");
					throw new RuntimeException();
				}
//			} catch (InterruptedException e) {
			} catch (Exception e) {
//				throw new RuntimeException();
//				continue;
//				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
	
	
}
