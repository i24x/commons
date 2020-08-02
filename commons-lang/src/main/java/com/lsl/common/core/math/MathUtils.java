package com.lsl.common.core.math;

import java.util.UUID;

/**
 * @Description
 * @Author yangcao
 * @Date 2019/10/12 11:31
 * @Version v1.0
 */
public class MathUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description
     * @Date 2019/10/12 12:55
     * @Param
     * @return
     **/
    public static int add(int a, int b) {
        int sum = a ^ b;
        int s = (a & b) << 1;
        while (s != 0) {
            // int tsum = sum;
            sum = sum ^ s;
            // s = (tsum&s)<<1;
            s = ((sum ^ s) & s) << 1;
        }
        return sum;
    }

    /**
     * @Description
     * @Date 2019/10/12 17:50
     * @Param
     * @return
     **/
    public int sum(int a, int b) {
        if (b == 0)
            return a;
        return sum(a ^ b, (a & b) << 1);
    }

    /**
     * @Description 公式：a>=0,-a=~a+1 证明：a+~a=2^w-1 a+~a+1 =2^w =0 -a=~a+1
     * @Date 2019/10/12 17:51
     * @Param
     * @return
     **/
    public static int sub(int a, int b) {
        return add(a, add(~b, 1));
    }

    /**
     * @Description
     * @Date 2019/10/12 17:58
     * @Param a 被乘数 000110101 左移 0001101010 00011010100
     * @Param b 乘数 10010111 右移
     * @return
     **/
    public static int multiply(int a, int b) {
        int m = 0;
        while (true) {
            if (b == 0) {
                break;
            }
            if ((b & 1) == 1) {// 取乘数的最后一位
                m = add(a, m);
            }
            a <<= 1;
            b >>>= 1;
        }
        return m;
    }

    /**
     * @Description 除法运算很容易想到可以转换成减法运算，即不停的用除数去减被除数， 直到被除数小于除数时，此时所减的次数就是我们需要的商，而此时的被除数就是余数。
     *              这里需要注意的是符号的确定，商的符号和乘法运算中乘积的符号确定一样，即取决于除数和被除数 ，同号为证，异号为负；余数的符号和被除数一样。
     * @Date 2019/10/15 9:29
     * @Param
     * @return
     **/
    public static int divide(int a, int b) {
        // 先取被除数和除数的绝对值
        int dividend = a > 0 ? a : add(~a, 1);
        int divisor = b > 0 ? b : add(~b, 1);

        int quotient = 0;// 商
        int remainder = 0;// 余数
        // 不断用除数去减被除数，直到被除数小于被除数（即除不尽了）
        while (dividend >= divisor) {// 直到商小于被除数
            quotient = add(quotient, 1);
            dividend = sub(dividend, divisor);
        }
        // 确定商的符号
        if ((a ^ b) < 0) {// 如果除数和被除数异号，则商为负数
            quotient = add(~quotient, 1);
        }
        // 确定余数符号
        remainder = b > 0 ? dividend : add(~dividend, 1);
        return quotient;// 返回商
    }

    public static int divide_v2(int a, int b) {
        // 先取被除数和除数的绝对值
        int dividend = a > 0 ? a : add(~a, 1);
        int divisor = b > 0 ? b : add(~b, 1);
        int quotient = 0;// 商
        int remainder = 0;// 余数
        for (int i = 31; i >= 0; i--) {
            // 比较dividend是否大于divisor的(1<<i)次方，不要将dividend与(divisor<<i)比较，而是用(dividend>>i)与divisor比较，
            // 效果一样，但是可以避免因(divisor<<i)操作可能导致的溢出，如果溢出则会可能dividend本身小于divisor，但是溢出导致dividend大于divisor
            if ((dividend >> i) >= divisor) {
                quotient = add(quotient, 1 << i);
                dividend = sub(dividend, divisor << i);
            }
        }
        // 确定商的符号
        if ((a ^ b) < 0) {
            // 如果除数和被除数异号，则商为负数
            quotient = add(~quotient, 1);
        }
        // 确定余数符号
        remainder = b > 0 ? dividend : add(~dividend, 1);
        return quotient;// 返回商
    }

    public static void main(String[] args) {
        byte b1 = 0b00111;
        byte b2 = 0b00000;

        System.out.println(b1 & b2);
        System.out.println(b1 | b2);

        System.out.println(b1 & (-b1));
        System.out.println(b1 & ~b1);
        System.out.println("===============================");

        System.out.println(add(12, 14));
        System.out.println(sub(30, 20));
        System.out.println(multiply(10, 20));
        System.out.println(multiply(-10, 20));
        System.out.println(multiply(-10, -20));
        // -3=~3+1
        // 证明: n+~n = 2^w-1 n+~n+1 = 2^w+1=0 数据溢出 -n = ~n+1
        System.out.println(divide(100, 3));

    }

    public static long idOfNanoTime() {
        return System.nanoTime();
    }

}
