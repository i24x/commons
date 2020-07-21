package cn.lsl.data.exchange.syncclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("receive")
public class ReceiveProperties {

    private String ip;

    private String port;

    private String tables;

    private String columns;

    private String conditions;

    private String syncColumn;
}
