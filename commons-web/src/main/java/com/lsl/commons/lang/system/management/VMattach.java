package com.lsl.commons.lang.system.management;

import java.io.IOException;
import java.io.InputStream;

import com.lsl.commons.lang.utils.system.management.JvmUtil;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import sun.tools.attach.HotSpotVirtualMachine;

public class VMattach {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        int vmPid = JvmUtil.getVMPid();
        VirtualMachine virtualMachine = VirtualMachine.attach("" + vmPid);
        HotSpotVirtualMachine hotSpotVirtualMachine = (HotSpotVirtualMachine)virtualMachine;
        InputStream inputStream = hotSpotVirtualMachine.remoteDataDump(new String[] {});

        byte[] buff = new byte[256];
        int len;
        do {
            len = inputStream.read(buff);
            if (len > 0) {
                String respone = new String(buff, 0, len, "UTF-8");
                System.out.print(respone);
            }
        } while (len > 0);
        inputStream.close();
        virtualMachine.detach();
    }
}
