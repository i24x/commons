package com.lsl.commons.lang.demo;

import java.nio.CharBuffer;

public class BufferTest {

    public static void main(String[] args) {
        String S1 = "123456789";
        char[] charArray = S1.toCharArray();
        CharBuffer cb = CharBuffer.allocate(50);
        for (char c : charArray) {
            cb.put(c);
            System.out.println("length:" + cb.length() + ",limit:" + cb.limit() + ",position:" + cb.position()
                + ",capacity:" + cb.capacity());
        }
        System.out.println("S1*******************************************");
        cb.flip();
        while (cb.hasRemaining()) {
            System.out.println(cb.get());
            System.out.println("length:" + cb.length() + ",limit:" + cb.limit() + ",position:" + cb.position()
                + ",capacity:" + cb.capacity());
        }
        // flip 把 limit 设为当前 position ，把 position 设为 0 ，一般在从 Buffer 读出数据前调用。
        System.out.println("S2->W*******************************************");
        cb.limit(cb.capacity());
        String S2 = "abcdefghijk";
        char[] charArray2 = S2.toCharArray();
        for (char c : charArray2) {
            cb.put(c);
            System.out.println("length:" + cb.length() + ",limit:" + cb.limit() + ",position:" + cb.position()
                + ",capacity:" + cb.capacity());
        }

        System.out.println("S2->R*******************************************");
        cb.flip();
        while (cb.hasRemaining()) {
            System.out.println(cb.get());
            System.out.println("length:" + cb.length() + ",limit:" + cb.limit() + ",position:" + cb.position()
                + ",capacity:" + cb.capacity());
        }
    }

}
