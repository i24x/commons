package com.lsl.dsp.number;

public class OperationCharTest {
    public static void main(String[] args) {
        // System.out.println(Integer.MAX_VALUE);
        // System.out.println(MathUtils.formatBinaryString(Integer.SIZE,
        // Integer.toBinaryString(Integer.MAX_VALUE)));
        // System.out.println(Base64.encode(""));
        System.out.println("https://blog.csdn.net/liushuijinger/article/details/7429197");
        System.out.println("https://blog.csdn.net/vickyway/article/details/48788769");
        System.out.println("原码：将一个整数，转换成二进制，就是其原码，最高是符号位。" + "\nX=-101011 , [X]原= 1 010 1011 ，[X]反=1 101 0100");
        System.out.println("反码：正数的反码就是其原码；负数的反码是将原码中，除符号位以外，每一位取反。");
        System.out
            .println("补码：正数的补码就是其原码；负数的反码+1就是补码。" + "\nX=-101011 , [X]原= 10101011 ，[X]反=1 101 0100，[X]补=11010101");
        System.out.println("-128:" + Integer.toBinaryString(-128));
        System.out.println("-127:" + Integer.toBinaryString(-127));
        System.out.println("-2  :" + Integer.toBinaryString(-2));
        System.out.println("-1  :" + Integer.toBinaryString(-1));
        System.out.println(" 1  :" + Integer.toBinaryString(1));
        System.out.println(" 2  :" + Integer.toBinaryString(2));
        System.out.println(" 127:" + Integer.toBinaryString(127));
        System.out.println(" 128:" + Integer.toBinaryString(128));
        System.out.println("数据交换演示");
        int a = 12;
        int b = 24;
        System.out.println("a:" + a + ",b:" + b);
        // a =a^b^a; b=a^b^b;
        a = a ^ b; // ==temp
        b = a ^ b; // ==> a
        a = a ^ b; // ==>temp^a[原]==temp^b
        System.out.println("a:" + a + ",b:" + b);

    }

}
