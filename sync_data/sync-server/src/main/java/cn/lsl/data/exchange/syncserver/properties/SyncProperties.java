package cn.lsl.data.exchange.syncserver.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("sync")
public class SyncProperties {

    private String tables;

    private String columns;
    
    private String id;
}
