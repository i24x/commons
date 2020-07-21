package com.lsl.common.bean;

public class FinallyReturn {

    public static int fin() {
        int x;
        try {
            x = 1;
            // return x;
        } catch (Exception e) {
            x = 2;
            // return x;
        } finally {
            x = 3;
            // return 3;
        }
        System.out.println(x);
        return x;
    }

    public static void main(String[] args) {
        System.out.println(fin());
    }

}

// return 1;

//
// public class com.lsl.common.bean.FinallyReturn {
// public com.lsl.common.bean.FinallyReturn();
// Code:
// 0: aload_0
// 1: invokespecial #1 // Method java/lang/Object."<init>":()V
// 4: return
//
// public static int fin();
// Code:
// 0: iconst_1
// 1: istore_0
// 2: iload_0
// 3: istore_1
// 4: iconst_3
// 5: istore_0
// 6: iload_1
// 7: ireturn
// 8: astore_1
// 9: iconst_2
// 10: istore_0
// 11: iload_0
// 12: istore_2
// 13: iconst_3
// 14: istore_0
// 15: iload_2
// 16: ireturn
// 17: astore_3
// 18: iconst_3
// 19: istore_0
// 20: aload_3
// 21: athrow
// Exception table:
// from to target type
// 0 4 8 Class java/lang/Exception
// 0 4 17 any
// 8 13 17 any
//
// public static void main(java.lang.String[]);
// Code:
// 0: getstatic #3 // Field java/lang/System.out:Ljava/io/PrintStream;
// 3: invokestatic #4 // Method fin:()I
// 6: invokevirtual #5 // Method java/io/PrintStream.println:(I)V
// 9: return
// }

// return 3

//
// public class com.lsl.common.bean.FinallyReturn {
// public com.lsl.common.bean.FinallyReturn();
// Code:
// 0: aload_0
// 1: invokespecial #1 // Method java/lang/Object."<init>":()V
// 4: return
//
// public static int fin();
// Code:
// 0: iconst_1
// 1: istore_0
// 2: iload_0
// 3: istore_1
// 4: iconst_3
// 5: istore_0
// 6: iconst_3
// 7: ireturn
// 8: astore_1
// 9: iconst_2
// 10: istore_0
// 11: iload_0
// 12: istore_2
// 13: iconst_3
// 14: istore_0
// 15: iconst_3
// 16: ireturn
// 17: astore_3
// 18: iconst_3
// 19: istore_0
// 20: iconst_3
// 21: ireturn
// Exception table:
// from to target type
// 0 4 8 Class java/lang/Exception
// 0 4 17 any
// 8 13 17 any
//
// public static void main(java.lang.String[]);
// Code:
// 0: getstatic #3 // Field java/lang/System.out:Ljava/io/PrintStream;
// 3: invokestatic #4 // Method fin:()I
// 6: invokevirtual #5 // Method java/io/PrintStream.println:(I)V
// 9: return
// }
