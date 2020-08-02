package com.lsl.common.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static void main(String[] args) {
        String regxp = "\\$\\{(.*?)\\}";
        String s = "${name}:address:${age}";
        System.out.println(RegexUtils.rmPlaceHolder(s, regxp, 1));
        System.out.println(Arrays.toString(RegexUtils.holderKeys(s, regxp, 1).toArray()));

        format();

    }

    public static List<String> holderKeys(String s, String regxp, int groupId) {
        List<String> keys = new ArrayList<>();
        if (s != null) {
            Pattern p = Pattern.compile(regxp);
            Matcher matcher = p.matcher(s);
            while (matcher.find()) {
                keys.add(matcher.group(groupId));
            }
        }
        return keys;
    }

    public static String rmPlaceHolder(String s, String regxp, int groupId) {
        List<String> keys = holderKeys(s, regxp, groupId);
        for (String key : keys) {
            s = s.replace("${" + key + "}", key);
        }
        return s;
    }

    public static void format() {
        String var1 = "%1$ 1.5s,KKK";

        System.out.println(String.format(var1, "A"));
    }
}
