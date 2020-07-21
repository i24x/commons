package com.lsl.common.vm.mbean;

import java.lang.management.*;
import java.util.Date;

/**
 * @author lisen
 * @date 2013-11-22 上午09:27:03
 */
public class VmInfoUtils {
    private VmInfoBean infoBean = null;

    private static class SingletonClassInstance {
        private static final VmInfoUtils instance = new VmInfoUtils();
    }

    public static VmInfoUtils getInstance() {
        return SingletonClassInstance.instance;
    }

    private VmInfoUtils() {
        infoBean = new VmInfoBean();
        // 操作系统信息
        OperatingSystemMXBean operateSystemMBean = ManagementFactory.getOperatingSystemMXBean();
        String operateName = operateSystemMBean.getName();
        infoBean.setOperateName(operateName);
        int processListCount = operateSystemMBean.getAvailableProcessors();
        infoBean.setProcessListCount(processListCount);
        String archName = operateSystemMBean.getArch();// System.getProperty("os.arch");
        infoBean.setArchName(archName);
        String versionName = operateSystemMBean.getVersion();// System.getProperty("os.version");
        infoBean.setVersionName(versionName);

        // 运行时信息
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String vmName = runtimeMXBean.getVmName();
        infoBean.setVmName(vmName);
        String vmVersion = runtimeMXBean.getVmVersion();
        // infoBean.setVmVersion(vmVersion);
        infoBean.setVmVersion(System.getProperty("java.version") + " (" + vmVersion + ")");
        String vmVendor = runtimeMXBean.getVmVendor();
        infoBean.setVmVendor(vmVendor);
        long startTime = runtimeMXBean.getStartTime();
        infoBean.setStartTime(new Date(startTime));

        infoBean.setArguments(runtimeMXBean.getInputArguments());
        // infoBean.setSystemProperties(runtimeMXBean.getSystemProperties());
    }

    public VmInfoBean getSystemInfo() {
        // 类信息
        ClassLoadingMXBean classLoadMXBean = ManagementFactory.getClassLoadingMXBean();
        int loadClazzCount = classLoadMXBean.getLoadedClassCount();
        infoBean.setLoadClazzCount(loadClazzCount);
        long hasloadClazzCount = classLoadMXBean.getTotalLoadedClassCount();
        infoBean.setHasloadClazzCount(hasloadClazzCount);
        long hasUnloadClazzCount = classLoadMXBean.getUnloadedClassCount();
        infoBean.setHasUnloadClazzCount(hasUnloadClazzCount);
        // 内存
        MemoryMXBean memoryUsage = ManagementFactory.getMemoryMXBean();
        infoBean.setHeapMemoryUsage(memoryUsage.getHeapMemoryUsage());
        infoBean.setNonHeapMemoryUsage(memoryUsage.getNonHeapMemoryUsage());
        return infoBean;
    }

    public static void main(String[] args) {
        VmInfoBean inf = VmInfoUtils.getInstance().getSystemInfo();
        System.out.println(inf);
    }
}