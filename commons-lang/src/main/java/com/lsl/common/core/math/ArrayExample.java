package com.lsl.common.core.math;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ArrayExample {
    public static void main(String[] args) {
        int[][] tab = {{1, 2, 3}, {2}, {4, 6, 9, 0}};
        // {A,B,X}

        int[] row = tab[0];
        for (int i : row) {
            System.out.println("col=" + i);
        }
        System.out.println("=================");

        for (int i = 0; i < tab.length; i++) {
            int[] rows = tab[i];
            System.out.println("=================");
            for (int row1 : rows) {
                System.out.println("col==>" + row1);
            }
        }

        // 如果是整数类型：那么默认值为0
        // 如果是浮点类型：那么默认值为0.0
        // 如果是字符类型：那么默认值为\u0000
        // 如果是布尔类型：那么默认值为false
        // 如果是引用类型：那么默认值为null

        // System.out.println(a1[0]);

        int[] a1 = {};//// 静态初始化，空数组，简写
        int[] a2 = new int[] {1, 2, 3, 8};//// 静态初始化
        int[] a3 = new int[2];//// 动态初始化
        int a4[] = new int[2];

        // Array
        System.out.println(Array.get(a2, 0));
        Array.set(a2, 0, 3);
        Integer[] a5 = (Integer[])Array.newInstance(Integer.class, 2);
        System.out.println(a5[0]);

        // Arrays

        Arrays.sort(a2);// 拷贝 填充 搜索 排序 转字符串 转流 转集合
        System.out.println(Arrays.toString(a2));// 转字符串
        int[] ints = Arrays.copyOfRange(a2, 1, 3);// 拷贝
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.binarySearch(a2, 8));// 排序
        Arrays.stream(a2).forEach(System.out::println);// 流
        List<Integer> integers = Arrays.asList(1, 5, 6, 9);// 转集合
        // integers.toArray();

        Arrays.fill(a4, 2);// 填充
        System.out.println(Arrays.toString(a4));
        System.out.println(Arrays.toString(a2));

        int sss = 0;
        Integer ss = new Integer(0);
        System.out.println(sss == ss);// 转化为基本数据类型处理

        Integer s = new Integer(0);

        System.out.println(s == ss);

    }

    public void f() {
        Integer ss = new Integer(0);
        // Integer s = new Integer(0);
        int s = 0;
        System.out.println(s == ss);
    }

    // 10: istore_2
    // 11: getstatic #3 // Field java/lang/System.out:Ljava/io/PrintStream;
    // 14: iload_2
    // 15: aload_1
    // 16: invokevirtual #32 // Method java/lang/Integer.intValue:()I
    // 19: if_icmpne 26
    // 22: iconst_1
    // 23: goto 27
    // 26: iconst_0
    // 27: invokevirtual #33 // Method java/io/PrintStream.println:(Z)V
    // 30: return

}
