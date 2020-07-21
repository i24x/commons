package com.lsl.common.juc.volatiles;

public class FinallyExample {

    public static int fin() {
        int a = 1;
        int b = 2;
        int c = 0;
        try {
            c = a + 1;
            return c;
        } catch (Exception e) {
            c = b + 1;
            return c;
        } finally {
            c = 4;
            // return c;
        }
    }

    // 0: iconst_1
    // 1: istore_0

    // 2: iconst_2
    // 3: istore_1

    // 4: iconst_0
    // 5: istore_2

    // 6: iload_0
    // 7: iconst_1
    // 8: iadd a+1

    // 9: istore_2 c=
    // 10: iload_2
    // 11: istore_3 c'=

    // 12: iconst_4
    // 13: istore_2 c=4

    // 14: iload_2
    // 15: ireturn return 4;

    // 16: astore_3
    // 17: iload_1 b
    // 18: iconst_1 1
    // 19: iadd
    // 20: istore_2 b+1
    // 21: iload_2 c=
    // 22: istore %4
    // 24: iconst_4 4
    // 25: istore_2 c=4
    // 26: iload_2
    // 27: ireturn 4

    // 28: astore %5
    // 30: iconst_4
    // 31: istore_2
    // 32: iload_2 c=4;
    // 33: ireturn return c;

    public static void main(String[] args) {
        System.out.println(fin());
    }
}
