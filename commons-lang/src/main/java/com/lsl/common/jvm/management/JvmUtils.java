package com.lsl.common.jvm.management;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.management.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.lsl.common.exception.BizRuntimeException;
import com.lsl.common.exception.SystemRuntimeException;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import sun.management.VMManagement;

public class JvmUtils {

 /*
    private static class SingletonClassInstance {
        private static final JvmUtil instance = new JvmUtil();
    }

    public static JvmUtil getInstance() {
        return SingletonClassInstance.instance;
    }
*/
    private JvmUtils() {}

    /**
     * @return
     */
    public static VmInfoBean getSystemInfo() {
        VmInfoBean infoBean = new VmInfoBean();
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

    public static final int getVMPid() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement)jvm.get(runtime);
            Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
            pidMethod.setAccessible(true);
            int pid = (Integer)pidMethod.invoke(mgmt);
            return pid;
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BizRuntimeException("获取进程ID失败", e);
        }
    }

    /**
     *
     * { @link https://docs.oracle.com/javase/8/docs/jdk/api/attach/spec/com/sun/tools/attach/VirtualMachine.html }
     * 
     * @param pid
     * @param ctf
     * @param jarPath
     *            agent.jar
     * @param agentArgs
     *            args
     * @return
     */
    public static VirtualMachine attach(String pid, ClassFileTransformer ctf, String jarPath, String agentArgs) {
        try {
            VirtualMachine attach = VirtualMachine.attach(pid);
            attach.loadAgent(jarPath, agentArgs);
            // agentmain premain
            return VirtualMachine.attach(pid);
        } catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
            throw new SystemRuntimeException("系统错误", e);
        }
    }

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
        VmInfoBean inf = JvmUtils.getSystemInfo();
        System.out.println(inf);
    }
}