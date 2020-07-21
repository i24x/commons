package com.lsl.common.vm.mbean;

import com.lsl.common.util.LogHome;
// import com.sun.tools.attach.AgentInitializationException;
// import com.sun.tools.attach.AgentLoadException;
// import com.sun.tools.attach.AttachNotSupportedException;
// import com.sun.tools.attach.VirtualMachine;
import sun.management.VMManagement;
//
// import java.io.IOException;
// import java.lang.instrument.ClassFileTransformer;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ProcessIdUtils {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // ManagementFactoryHelper
        LogHome.info("VMPid:" + getVMPid());
    }

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

    // /**
    // * @see https://docs.oracle.com/javase/8/docs/jdk/api/attach/spec/com/sun/tools/attach/VirtualMachine.html
    // * @param pid
    // * @return
    // * @throws IOException
    // * @throws AttachNotSupportedException
    // */
    // public static VirtualMachine attach(String pid, ClassFileTransformer ctf) throws IOException,
    // AttachNotSupportedException, AgentLoadException, AgentInitializationException {
    // VirtualMachine attach = VirtualMachine.attach(pid);
    // attach.loadAgent("agent.jar","args");
    // //agentmain premain
    // return VirtualMachine.attach(pid);
    // }

}
