package com.lsl.common.bean;

public class HelloBean {

    public void sayHello() {
        int d = 50;
        int a = 100;
        int b = 2;
        int c = a + b;
        System.out.println(c);
    }

    public int sayHi() {
        int a = 100;
        int b = 200000;
        int c = a + b;
        return c;
    }

    public int sayGoodBye(int divide) {
        int c = 0;
        try {
            c = 1000 / divide;
            return c;
        } catch (Exception e) {
            c = 500;
            return c;
        } finally {
            c = 300;
            // return c;
        }
    }

    public static void main(String[] args) {
        System.out.println(new HelloBean().sayGoodBye(0));
    }

    // public int sayGoodBye(int);
    // descriptor: (I)I
    // flags: ACC_PUBLIC
    // Code:
    // stack=2, locals=6, args_size=2
    // 0: iconst_0
    // 1: istore_2
    // 2: sipush 1000
    // 5: iload_1
    // 6: idiv
    // 7: istore_2
    // 8: iload_2
    // 9: istore_3
    // 10: sipush 300
    // 13: istore_2
    // 14: iload_2
    // 15: ireturn
    // 16: astore_3
    // 17: sipush 500
    // 20: istore_2
    // 21: iload_2
    // 22: istore 4
    // 24: sipush 300
    // 27: istore_2
    // 28: iload_2
    // 29: ireturn
    // 30: astore 5
    // 32: sipush 300
    // 35: istore_2
    // 36: iload_2
    // 37: ireturn
    // Exception table:
    // from to target type
    // 2 10 16 Class java/lang/Exception
    // 2 10 30 any
    // 16 24 30 any
    // 30 32 30 any
    // LineNumberTable:
    // line 22: 0
    // line 24: 2
    // line 25: 8
    // line 30: 10
    // line 31: 14
    // line 26: 16
    // line 27: 17
    // line 28: 21
    // line 30: 24
    // line 31: 28
    // line 30: 30
    // line 31: 36
    // LocalVariableTable:
    // Start Length Slot Name Signature
    // 17 13 3 e Ljava/lang/Exception;
    // 0 38 0 this Lcom/lsl/common/vm/bean/HelloBean;
    // 0 38 1 divide I
    // 2 36 2 c I
    // StackMapTable: number_of_entries = 2
    // frame_type = 255 /* full_frame */
    // offset_delta = 16
    // locals = [ class com/lsl/common/vm/bean/HelloBean, int, int ]
    // stack = [ class java/lang/Exception ]
    // frame_type = 77 /* same_locals_1_stack_item */
    // stack = [ class java/lang/Throwable ]
}
