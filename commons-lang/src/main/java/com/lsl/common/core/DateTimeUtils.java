package com.lsl.common.core;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具 时间转化为时间瞬间 时间瞬间+时区转化为本地时间 本地时间+时区转化为时间瞬间
 */
public class DateTimeUtils {

    private DateTimeUtils() {}

    // 01. DATE --> LocalDateTime
    public static LocalDateTime UDateToLocalDateTime(Date date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, zone);
    }

    // 02. DATE --> LocalDate
    public static LocalDate UDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    // 03. DATE --> LocalTime
    public static void UDateToLocalTime(Date date) {
        Instant instant = date.toInstant();// 时间片段
        ZoneId zone = ZoneId.systemDefault();// 时区
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
    }

    // 04. LocalDateTime --> DATE
    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    // 05. LocalDate --> DATE
    public static Date LocalDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    // 06. LocalTime --> DATE
    public static Date LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date dateNow() {
        Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static void main(String[] args) {

        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()));;
        // 格式化之后的值是 2018-09-25

        // 20170101  此方法也可以用来生成一个时间戳的啦
        System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.of(2017, 1, 1)));

        // 2017-01-01T09:10:00
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.of(2017, 1, 1, 9, 10, 0)));

        // 2017-02-27 22:48:52
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));

        String d = "2019-09-30 14:05:55";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime l = LocalDateTime.parse(d, f);

        System.out.println(l.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")));
    }

}
