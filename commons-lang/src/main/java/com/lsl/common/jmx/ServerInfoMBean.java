package com.lsl.common.jmx;

public interface ServerInfoMBean {
    int getExecutedSqlCmdCount();

    void setExecutedSqlCmdCount(int count);
}
