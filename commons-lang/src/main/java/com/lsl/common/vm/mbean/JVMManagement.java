package com.lsl.common.vm.mbean;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class JVMManagement {
    /**
     * 查找所有死锁线程
     * 
     * @return
     */
    public static long[] findDeadlockedThreads() {
        return ManagementFactory.getThreadMXBean().findDeadlockedThreads();
    }

    /**
     * 根据Id查找线程
     * 
     * @return
     */
    public static ThreadInfo[] getThreadInfo(long[] ids) {
        return ManagementFactory.getThreadMXBean().getThreadInfo(ids, true, true);
    }

    public static void main(String[] args) {
        ThreadInfo[] threadInfo = JVMManagement.getThreadInfo(findDeadlockedThreads());
    }
}
