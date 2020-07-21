package com.lsl.common.datastruct;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;

public class Dprograme_Queue {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("ABS129J<L");
        System.out.println(text.reverse());

        // 97-122 a-z 65-90 A-Z
        for (int i = 'A'; i <= 'Z'; i++) {
            System.out.println(i);
        }

        Stream<String> sorted = Stream.of("A", "z", "C", "JK", "s").sorted();
        sorted.forEach(System.out::println);

        int[] high = {176, 163, 150, 180, 170, 130, 167, 160};

        int[] up = new int[8]; // 记录每位同学的最大上升子序列

        int[] down = new int[8]; // 记录每位同学的最大下降子序列

        for (int i = 0; i < high.length; i++) {
            up[i] = 1;
            // 每位同学的最大上升子序列初始值为1
            for (int j = 0; j < i; j++) {
                if ((high[j] < high[i]) && (up[i] < up[j] + 1)) {
                    up[i] = up[j] + 1;
                }
                // 前i位同学的最大上升子序列的最大值再加1
            }
        }

        for (int i = high.length - 1; i >= 0; i--) {
            down[i] = 1;
            for (int j = high.length - 1; j > i; j--) {
                if ((high[j] < high[i]) && (down[i] < down[j] + 1)) {
                    down[i] = down[j] + 1;
                }
                // 后N-i位同学的最大下降子序列的最大值再加1
            }
        }

        int max = 0;
        // 设每位同学所形成的最长合唱队形的最大值初值为0
        int index = 0;
        // 设最大值对应的索引为0
        for (int i = 0; i < high.length; i++) {
            if (up[i] + down[i] - 1 > max) {
                max = up[i] + down[i] - 1;
                // 求得每位同学所形成的最长合唱队形的最大值
                index = i;
                // 求得对应的索引
            }
        }

        System.out.println("最长合唱队形的长度为：" + max);
        System.out.println("对应的是第" + (index + 1) + "位同学，需要" + (high.length - max) + "位同学出列");

        Scanner s = new Scanner(System.in);
        System.out.println("INPUT");
        while (s.hasNext()) {
            System.out.println(s.next());
            System.out.println("INPUT");
        }

    }
}
