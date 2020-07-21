package com.lsl.commons.zookeeper.api;

import com.lsl.commons.lang.zookeeper.ZookeeperConfig;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class ZkClientBuilder {

    public static final String defaultServers = ZookeeperConfig.SERVERS;

    public static ZkClient create(ZkClientConfig config) {
        ZkConnection zkConn = new ZkConnection(config.getConnectionAddr());
        ZkClient zk = new ZkClient(zkConn, config.getSessionOutTime());
        return zk;
    }

    public static ZkClient createDefault() {
        ZkClientConfig config = new ZkClientConfig(defaultServers);
        ZkConnection zkConn = new ZkConnection(config.getConnectionAddr());
        return new ZkClient(zkConn, config.getSessionOutTime());
    }

}
