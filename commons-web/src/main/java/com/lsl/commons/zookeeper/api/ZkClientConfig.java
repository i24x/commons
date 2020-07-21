package com.lsl.commons.zookeeper.api;

public class ZkClientConfig {
    /** zookeeper地址 */
    public String zkServers;
    /** session超时时间 */
    public int sessionTimeOutMs = 5000;

    public String getConnectionAddr() {
        return zkServers;
    }

    public void setZkServers(String zkServers) {
        this.zkServers = zkServers;
    }

    public int getSessionOutTime() {
        return sessionTimeOutMs;
    }

    public void setSessionOutTime(int sessionOutTime) {
        sessionTimeOutMs = sessionOutTime;
    }

    public ZkClientConfig(String addr) {
        zkServers = addr;
    }

    public ZkClientConfig() {

    }

}
