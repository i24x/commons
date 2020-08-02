package com.lsl.common.concurrent.util;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {
	public static final int NUM = 10;
	public static void exec(String msg, int second) {
		info(msg+"=======================>>>>>>>>");
		ThreadUtil.waitSeconds(second);
		info(msg+"=======================<<<<<<<<");
	}
	public static void waitSeconds(int sleepTime) {
		try {
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void waitOneSecond() {
		waitSeconds(1);
	}
	public static void execute(int second) {
		ThreadUtil.waitSeconds(second);
	}
	
	public static void info(String msg){
		String info_prefix = "[ThreadId:"+Thread.currentThread().getId()
				+",ThreadName:"+Thread.currentThread().getName()+"]-";
		msg = info_prefix+msg;
		System.out.println(msg);
	}
}
