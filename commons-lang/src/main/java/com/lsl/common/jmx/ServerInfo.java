package com.lsl.common.jmx;

import javax.management.MXBean;

@MXBean
public class ServerInfo implements ServerInfoMBean {

    int count;

    public int getExecutedSqlCmdCount() {
        return count;
    }

    @Override
    public void setExecutedSqlCmdCount(int count) {
        this.count = count;
    }
}
