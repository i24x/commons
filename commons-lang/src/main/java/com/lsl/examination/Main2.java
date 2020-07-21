package com.lsl.examination;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        int m;
        try {
            m = Integer.parseInt(n);
            if (m < 1) {
                System.out.println("ERROR");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR");
            return;
        }

        int before = m;
        int after = m;
        boolean hasFind = false;
        if (isDCnum(m)) {
            System.out.println(m);
            return;
        }
        for (;;) {
            before--;
            after++;
            if (before > 0 && isDCnum(before)) {
                hasFind = true;
            }
            if (after <= Integer.MAX_VALUE && isDCnum(after)) {
                hasFind = true;
            }
            if (hasFind) {
                break;
            }
        }
        if (isDCnum(before) && isDCnum(after)) {
            System.out.println(before + "," + after);
            return;
        }
        if (isDCnum(before)) {
            System.out.println(before);
        }
        if (isDCnum(after)) {
            System.out.println(after);
        }
    }

    private static boolean isDCnum(int num) {
        String s = String.valueOf(num);
        if (s.length() <= 1) {
            return false;
        }
        int len = s.length();
        String left = "";
        String right = "";
        int mid = len / 2;
        left = s.substring(0, mid);
        if (len % 2 == 0) {
            right = new StringBuilder(s.substring(mid, len)).reverse().toString();
        } else {
            right = new StringBuilder(s.substring(mid + 1, len)).reverse().toString();
        }
        return left.equals(right);
    }
}
