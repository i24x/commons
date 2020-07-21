package com.lsl.commons.lang.demo;

import java.util.Date;

public class StringDemo {
    public static void main(String[] args) {
        // join
        System.out.println(String.join(",", "1", "2", "3"));
        System.out.println(Math.min(15, 12));
        // format
        System.out.println(String.format("%+.2f", Math.cos(30)));
        System.out.println(String.format("%.2f", 12.789));
        System.out.println(String.format("%1$,d-%2$,d", 12302562, 12344));
        System.out.println(String.format("asasas %1$s ass %2$s  ", "12302562", "12344"));
        //
        String string = "12345678";
        string.getBytes();

        Date date = new Date(); // 创建日期对象
        System.out.printf("全部日期和时间信息：%tc%n", date);
        // 格式化输出日期或时间
        System.out.printf("年-月-日格式：%tF%n", date);
        System.out.printf("月/日/年格式：%tD%n", date);
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        System.out.printf("HH:MM格式（24时制）：%tR", date);
    }
}
