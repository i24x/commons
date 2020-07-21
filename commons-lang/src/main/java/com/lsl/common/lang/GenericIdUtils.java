package com.lsl.common.lang;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class GenericIdUtils {
    public static void main(String[] args) {

        // char A = 'A';
        // char Z = 'Z';
        // String lastChars = "";
        // for (int i = 0; i <50; i++) {
        // if("".equals(lastChars)){
        // lastChars = "A";
        // }else {
        // char lastChar = lastChars.charAt(lastChars.length()-1);
        // if(lastChar=='Z'){
        // lastChars = lastChars.substring(lastChars.length())+"AA";
        // }else {
        // lastChars = lastChars.substring(lastChars.length()-1) + (char) (lastChar + 1);
        // }
        // }
        // System.out.println(lastChars);
        // }
        // for(int i = A;i<=Z;i++){
        // System.out.println((char)i);
        // }
        DecimalFormat df = new DecimalFormat("0.00");
        String str = df.format(new BigDecimal("100"));
        System.out.println(formatNumber("0.000"));

    }

    private static String formatNumber(String m) {
        if (NumberUtils.isNumber(m)) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(new BigDecimal(m));
        }
        return "0.00";
    }
}
