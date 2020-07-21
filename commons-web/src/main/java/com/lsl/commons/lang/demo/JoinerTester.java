package com.lsl.commons.lang.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;

public class JoinerTester {

    public static void main(String[] args) {
        System.out.println(StringUtils.leftPad("333", 10, "0"));
        StringTokenizer st = new StringTokenizer(String.join("Harry", null, "Ron", "Hermione"), ";");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
        System.out.println(System.identityHashCode("test".intern()));
        System.out.println(System.identityHashCode("test".intern()));
        System.out.println(System.identityHashCode("Test"));
        System.out.println(System.identityHashCode("Test"));
        System.out.println(System.identityHashCode(new String("Test")));
        System.out.println(System.identityHashCode(new String("Test")));
        System.out.println("Year:" + new Date(Long.MAX_VALUE).getYear());
        System.out.println(Calendar.getInstance(Locale.CHINA).getTime());
        System.out.println(Calendar.getInstance(Locale.UK).getTime());
        System.out.println(Calendar.getInstance(TimeZone.getDefault()).getTime());
        System.out.println(Calendar.getInstance(Locale.UK).getTime());

        System.setProperty("user.timezone", "Asia/Shanghai");
        Calendar calendar = Calendar.getInstance();
        System.out.println("目前时间：" + calendar.getTime());
        System.setProperty("user.timezone", "America/Bahia");
        System.out.println("目前时间：" + Calendar.getInstance().getTime());

        System.out.println("Calendar时区：：" + calendar.getTimeZone().getID());
        System.out.println("user.timezone：" + System.getProperty("user.timezone"));
        System.out.println("user.country：" + System.getProperty("user.country"));
        System.out.println("默认时区：" + TimeZone.getDefault().getID());
        try {
            // Runtime.getRuntime().exec("calc");
            // cygdrive
            Process exec = Runtime.getRuntime().exec("mspaint");// pwd ls tree
            InputStream inputStream = exec.getInputStream();
            InputStreamReader r = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(r);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.synchronizedMap(new HashMap());
    }

}
