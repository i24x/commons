package cn.lsl.data.exchange.syncserver;

import cn.lsl.data.exchange.syncserver.netty.NettyServer;
import cn.lsl.data.exchange.syncserver.properties.SyncProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SyncServerApplication implements CommandLineRunner{

    @Value("${netty.port}")
    private int port;

    @Autowired
	private NettyServer nettyServer;

	public static void main(String[] args) {
		SpringApplication.run(SyncServerApplication.class, args);
	}

	@Bean
	public SyncProperties syncProperties(){
		return new SyncProperties();
	}

    @Override
    public void run(String... args) throws Exception {
		nettyServer.run(port);
    }
}
