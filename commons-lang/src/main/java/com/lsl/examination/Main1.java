package com.lsl.examination;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main1 {
    public static void main(String[] args) {
        String next = "";
        // 格式校验
        Pattern compile = Pattern.compile("[0-1]{0,5}");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            next = scanner.next();
            if (next != null && next.length() > 0) {
                String[] split = next.split(",");
                boolean f = true;
                for (String s : split) {
                    if (s.length() > 5) {
                        f = false;
                        System.out.println("请重新输入");
                    }
                    Matcher matcher = compile.matcher(s);
                    if (!matcher.matches()) {
                        f = false;
                        System.out.println("请重新输入");
                    }
                }
                if (f) {
                    break;
                }
            } else {
                System.out.println("请重新输入");
            }
        }
        String[] menmbers = next.split(",");

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;

        for (String menmber : menmbers) {
            if (menmber.length() < 5) {
                menmber = "00000".substring(menmber.length()) + menmber;
                System.out.println(menmber);
            }
            char[] chars = menmber.toCharArray();
            if (chars[0] == '1') {
                count1++;
            }
            if (chars[1] == '1') {
                count2++;
            }
            if (chars[2] == '1') {
                count3++;
            }
            if (chars[3] == '1') {
                count4++;
            }
            if (chars[1] == '1') {
                count5++;
            }
        }
        List<Integer> collect = Stream.of(count1, count2, count3, count4, count5).sorted().collect(Collectors.toList());
        System.out.println(collect.get(0));
    }
}
