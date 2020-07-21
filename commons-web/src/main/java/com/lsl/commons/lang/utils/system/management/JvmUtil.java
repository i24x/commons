package com.lsl.commons.lang.utils.system.management;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
// Access restriction: The type VMManagement is not accessible due to
// restriction on required library H:\env\jdk7\jre\lib\rt.jar
import sun.management.VMManagement;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JvmUtil {
    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        // get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // ManagementFactoryHelper
        System.out.println("Pid is:" + getVMPid());

        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
    }

    @SuppressWarnings("restriction")
    public static final int getVMPid() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("vm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement)jvm.get(runtime);
            Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
            pidMethod.setAccessible(true);
            int pid = (Integer)pidMethod.invoke(mgmt);
            return pid;
        } catch (Exception e) {
            return -1;
        }
    }
}
