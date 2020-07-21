package com.lsl.commons.lang.utils.system.bean.jvmargs;

import java.util.Vector;

public class Test03 {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {

        // -Xms1m -Xmx1m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/Test03.dump
        // 堆内存溢出
        Vector v = new Vector();
        for (int i = 0; i < 5; i++) {
            v.add(new Byte[1 * 1024 * 1024]);
        }

    }
}
