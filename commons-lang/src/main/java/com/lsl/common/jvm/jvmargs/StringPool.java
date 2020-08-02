package com.lsl.common.jvm.jvmargs;

/**
 * @author yang.cao
 * @version 1.0
 */

public class StringPool {
    public static void main(String[] args) {

        // String abc1 = "abc";
        // String abc2 = "abc";
        //
        // String abc3 = new String("abc");
        // String abc4 = new String("abc");
        // abc4.intern();
        //
        // //地址
        // System.out.println(System.identityHashCode(abc1));
        // System.out.println(System.identityHashCode(abc2));
        //
        // System.out.println(System.identityHashCode(abc3));
        // System.out.println(System.identityHashCode(abc4));
        // System.out.println(abc1==abc2);
        // System.out.println(abc1==abc3);
        // System.out.println(abc1==abc4);

        String s = new String("1");
        s.intern();// 无意义
        String s2 = "1";
        System.out.println(s == s2);
        System.out.println(s.intern() == s2);

        String s3 = new String("1") + new String("1");
        String s5 = new String("11");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s5.intern() == s4);

        // false
        // true

        // String s = new String("1");
        // String s2 = "1";
        // s.intern();
        // System.out.println(s == s2);
        //
        // String s3 = new String("1") + new String("1");
        // String s4 = "11";
        // s3.intern();
        // System.out.println(s3 == s4);

        // false
        // false
    }
}
