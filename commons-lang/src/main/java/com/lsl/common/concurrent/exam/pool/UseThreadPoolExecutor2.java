package com.lsl.common.concurrent.exam.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UseThreadPoolExecutor2 implements Runnable{

	private static AtomicInteger count = new AtomicInteger(0);
	
	@Override
	public void run() {
//		try {
			int temp = count.incrementAndGet();
			System.out.println("任务" + temp);
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) throws Exception{
		//System.out.println(Runtime.getRuntime().availableProcessors());
		BlockingQueue<Runnable> queue = 
				//new LinkedBlockingQueue<Runnable>();
				new ArrayBlockingQueue<Runnable>(5);
		ExecutorService executor  = new ThreadPoolExecutor(
					5, 		//core
					5, 	//max
					120L, 	//2fenzhong
					TimeUnit.SECONDS,
					queue,new RejectedHandler());
		//JDK拒绝策略 AbortPolicy
		
		for(int i = 0 ; i < 20; i++){
			executor.execute(new UseThreadPoolExecutor2());
		}
		
	}


}
