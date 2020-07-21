package com.lsl.commons.lang.utils.system.bean.jvmargs;

public class Test07 {
    @SuppressWarnings("unused")
    public static void alloc() {
        byte[] b = new byte[2];
    }

    public static void main(String[] args) {
        // TLAB分配
        // 参数：-XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400 -XX:-ResizeTLAB
        // -XX:TLABRefillWasteFraction=100 -XX:-DoEscapeAnalysis -server
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
    }
}
