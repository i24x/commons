package com.lsl.commons.lang.demo;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTester {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        // now = LocalDate.of(2018, 10, 30);
        // now = now.plus(12, ChronoUnit.DAYS);
        now = now.plusYears(16);
        now = now.plusDays(12);
        System.out.println(LocalDate.parse("2018-07-05 12:24:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(LocalDate.now().format(f));
        System.out.println(now.isAfter(LocalDate.now()));
        System.out.println(now.isLeapYear());
        LocalTime time = LocalTime.now();
        MonthDay birdate = MonthDay.of(6, 24);
        MonthDay dayOfMonth = MonthDay.from(LocalDate.now());
        System.out.println("======>" + dayOfMonth.equals(birdate));
        // time.format(DateTimeFormatter.ISO_TIME);
        System.out.println(time);
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
        LocalDateTime ltime = LocalDateTime.now();
        System.out.println("LocalDateTime===>" + ltime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Clock c = Clock.systemUTC();
        System.out.println(c);
        System.out.println(c.millis());
        Period between = Period.between(LocalDate.now(), now);
        System.out.println(between.getYears());
        System.out.println(between.getDays());
        System.out.println(between.getMonths());
        // LocalDate->Date
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        System.out.println("LocalDate = " + localDate);
        System.out.println("Date = " + date);
        // Date ->LocalDate
        date = new Date();
        Instant instant = date.toInstant();
        zoneId = ZoneId.systemDefault();
        localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + localDate);
    }
}
