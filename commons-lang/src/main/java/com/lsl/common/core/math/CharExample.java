package com.lsl.common.core.math;

import java.io.UnsupportedEncodingException;

public class CharExample {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "编";
        byte[] gbks = s.getBytes("GBK");
        System.out.println(gbks.length);
        byte[] utf8s = s.getBytes("UTF-8");
        System.out.println(utf8s.length);
        // Java字符串与字符集的基本概念 如果使用unicode/UTF-16则4个字节(开头的两个字节是一个mark)
        byte[] unicodes = s.getBytes("Unicode");
        System.out.println(unicodes.length);
        System.out.println("编码".getBytes("Unicode").length);
        System.out.println("编码集".getBytes("Unicode").length);

        char c = s.charAt(0);
        System.out.println((int)c);

        Character c1 = 32534;
        System.out.println("\\u" + Integer.toHexString(c1));

        char c2 = '\u7f16';
        System.out.println(c2);

        char c3 = '编';
        System.out.println(c3);
        char c4 = 0x7f16;
        System.out.println(c4);
    }
}
