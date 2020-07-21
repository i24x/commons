package com.lsl.commons.lang.demo;

import org.apache.hadoop.hbase.mapreduce.HashTable;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StringPool {
    public static void main(String[] args) throws IOException {
        String s1 = "abcd";
        String s2 = "abc" + new String("d");
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
        System.out.println(s1 == s2);

        // ===============================
        // System.in.read();

        Set s = new HashSet<>();

        s.add(null);
        Map m = new HashMap();

        Collections.synchronizedMap(m);

        Set<String> st = new TreeSet<>();
        st.add("");
        st.add("");
        st.add("");

        st.add("");

        Integer integer = new Integer("1");
        System.out.println(integer == 1);

        Map<String, String> c = new Hashtable<>();
        Map<String, String> cm = new ConcurrentHashMap();

        System.out.println(new Long(1000).equals(1000)); // false
        System.out.println(new Long(1000).equals(1000L)); // true
        System.out.println(new Long(1000) == 1000); // true
        System.out.println(new Long(1000) == 1000); // trueL

        System.out.println(new Long(1000) == (new Long(1000)));
        System.out.println(new Integer(100) == (new Integer(100)));

        Integer cache1 = Integer.valueOf(1);
        Integer cache2 = Integer.valueOf(1);

        Integer cache3 = Integer.getInteger("1");
        Integer new1 = new Integer(1);

        System.out.println(cache1 == cache2);
        System.out.println(cache1 == cache3);
        System.out.println(cache1 == new1);

        // Integer i= null;
        // System.out.println(i==0);

        Integer x = 1;
        Integer y = 1;
        System.out.println(x == y);

        byte b = 12;
        short sh = 123;

        char ch = 65535;
        int t = b + sh;
        int t2 = b + ch;

        long l = 1L;

        long l1 = l + b;

        float f = 1f;

        // long lo = 21000000000;
        long lo = 21000000000L;

        // 两者的主要区别如下：
        //
        // 01.在内存中占有的字节数不同
        //
        // 单精度浮点数在机内存占4个字节
        //
        // 双精度浮点数在机内存占8个字节
        //
        // 02.有效数字位数不同
        //
        // 单精度浮点数有效数字8位
        //
        // 双精度浮点数有效数字16位
        //
        // 03.数值取值范围
        //
        // 单精度浮点数的表示范围：-3.40E+38~3.40E+38
        //
        // 双精度浮点数的表示范围：-1.79E+308~-1.79E+308
        //
        // 04.在程序中处理速度不同
        //
        // 一般来说，CPU处理单精度浮点数的速度比处理双精度浮点数快
        //
        // 如果不声明，默认小数为double类型，所以如果要用float的话，必须进行强转
        //
        // 例如：float a=1.3; 会编译报错，正确的写法 float a = (float)1.3;或者float a = 1.3f;（f或F都可以不区分大小写）

        double d = 1234567890123456789012345678.90123456789012345678901234567890d;
        double d2 = 1.0d;

        System.out.println("d:" + (d + d2));

        System.out.println(Double.valueOf(d).toString());
        float fl = 123456789999999999900000000000000000000f;// 8
        float fs = 100000000000000000000000000000.000000000f;
        System.out.println(Float.valueOf(fl).toString());
        // 数据转换
        // Java中数据类型转换必须满足如下规则：
        //
        // 1.不能对boolean类型进行类型转换
        // 2.不能把对象类型转换成不相关类的对象
        // 3.小的数据类型与大的数据类型做数值运算时，小的数据类型会自动提升为大的数据类型。
        // 4.大的数据类型要转为小的数据类型必须强转，强转可能会丢失数据。
        // 5.浮点数到整数的转换是通过舍弃小数得到，而不是四舍五入
        // 转换从低级到高级
        // 低 --------------------> 高
        // byte,short,char->int->long->float->double

        BigDecimal bd = new BigDecimal(12.6);
        bd.add(new BigDecimal(12.6));

        System.out.println(Float.valueOf(fl).intValue());
        System.out.println("10000000000000000000000000000000000000".length());
        System.out.println("111111111111111111111111".length());

        System.out.println(t);

        // 小数转化为二进制
        // 例如32位的浮点数18.75 的二进制形式是：10010.11， 1.5 0.5 0.9 1.8 1.6 1.2 0.4 0.8 1.6 1.2 0.4 0.8
        // 用IEEE754标准表示方法如下， 9/0 4/1 2/0 1/0 0/1 左边除2取余 右边乘2，取整数。取小数再乘2 111001100
        // 把小数点移动e位，使小数点左边只有一位有效数字1，这样e就是这个数的阶码的真值；对于这个数，把
        // 小数点向左移4位，变成了1.001011，e=4，即真值阶码为4，则阶码E=e+127，用二进制表示为10000011，
        //
        // E=10000011就是18.75这个数的阶码。这个数为正，因此数符位为0，18.75在计算机内的表示为
        // 0 10000011 0010110 00000000 00000000
        // 这样-18.75在计算中的表示为 1 10000011(131) 0010110 00000000 00000000

        //
        // 比如8.25，二进制科学计数法表示为：1.00001*2^3，具体转换方法：8的二进制1000；.25的二进制.01：
        // 即0.25*2 取整数 0.5*2 取整。写为：1000.01，小数点左移3位，即转换完毕。 二进制计数法
        // 符号位确定：8.25为正数，符号位为0。
        // 阶码的表示：阶码位3+127=130；二进制10000010，已经是8位。 指数范围就是整数范围 2^-128(2^7) 2^127=10^38
        // 2^(2^-10) 2^(2^10)
        // 尾数的表示：去掉小数点前面的1，为00001，后面补充0至23位：000 0100 0000 0000 0000 0000
        // 最终8.25在内存里存储的二进制为：0 10000010 000 0100 0000 0000 0000 0000 小数部分 2^24-1=8位有效数字

        // 各部分占用位宽如下所示：
        //
        //              符号位     阶码      尾数     长度
        //
        // float           1         8        23      32
        //
        // double          1         11        52      64 2^53-1位有效数字

        switch ("1") {
            case "1": {
                System.out.println("1");
            }
            case "2": {
                System.out.println("2");
                System.out.println("break");
                break;
            }
            default: {
                System.out.println("0");
            }
        }
        // byte con = 1;
        // char con = 1;
        // short con = 1;
        // char con = 1;
        // int con = 1;
        String con = "2";

        // float con = 1;
        // double con = 1;
        // long con = 1;

        switch (con) {
            case "1":
                System.out.println("SSSSS" + 1);
            case "2":
                System.out.println("SSSSS" + 2);

        }

    }

}
