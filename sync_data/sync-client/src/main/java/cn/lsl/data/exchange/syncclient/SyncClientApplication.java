package cn.lsl.data.exchange.syncclient;

import cn.lsl.data.exchange.syncclient.properties.ReceiveProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SyncClientApplication {

	@Value("${task.size}")
	private int taskSize;

	public static void main(String[] args) {
		SpringApplication.run(SyncClientApplication.class, args);
	}

	@Bean
	public ReceiveProperties receiveProperties(){
		return new ReceiveProperties();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(taskSize);
		return taskScheduler;

	}
}
