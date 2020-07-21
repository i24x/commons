package com.lsl.common.lang.math;

/**
 * @Author yangcao
 * @Description xor or and not
 * @Date 2019/10/12 11:31
 * @Version v1.0
 */
public class BitUtils {
    /**
     * @return
     * @Description
     * @Date 2019/10/12 20:46
     * @Param
     **/
    public static int and(int a, int b) {
        return a & b;
    }

    /**
     * @return
     * @Description 取最后一位
     * @Date 2019/10/12 18:04
     * @Param
     **/
    public static int lastBit(int a) {
        return a & 1;
    }

    /**
     * @return
     * @Description 去掉最后一位高位 000110 000101
     * @Date 2019/10/12 18:05
     * @Param
     **/
    public static int lastHighBit(int a) {
        return a & (a - 1);
    }

    /**
     * @return 是否为2的N次幂
     * @Description 判断输入参数a是否为2的N次幂
     * @Date 2019/10/15 9:54
     * @Param a
     **/
    public static boolean twoNthPower(int a) {
        return (a & (a - 1)) == 0;
    }

    public static int calcHighBitCount(int a) {
        int c = 0;
        if (a != 0) {
            a = lastHighBit(a);
            c++;
        }
        return c;
    }

    /**
     * @return
     * @Description 获取INT最大值 0111 1111 .....
     * @Date 2019/10/15 10:27
     * @Param
     **/
    public static int maxIntValue() {
        return (1 << 31) - 1; // ~(1<<31)
    }

    /**
     * @return
     * @Description 获取INT最小值 1000 0000 0000 .......
     * @Date 2019/10/15 10:44
     * @Param
     **/
    public static int minIntValue() {
        return 1 << 31; // 1000000 -x = ~x+1 -128=~128+1 2^7
        // 0 1000 0000
        // 1 0111 1111
        // 1 1100 0000
    }

    /**
     * @return 乘以2的m次方
     * @Description
     * @Date 2019/10/15 10:47
     * @Param
     **/
    public static int multiply2Power(int a, int m) {
        return a << m;
    }

    /**
     * @return
     * @Description 偶数奇数判断
     * @Date 2019/10/15 10:47
     * @Param
     **/
    boolean isOddNumber(int n) {
        return (n & 1) == 1;
    }

    /**
     * @Description
     * @Date 2019/10/15 10:49
     * @Param
     * @return
     **/
    public static int abs(int n) {
        return (n ^ (n >> 31)) - (n >> 31); // n^-1+1=~n+1
        /*
         1.n>>31 取得n的符号，若n为正数，n>>31等于0，若n为负数，n>>31等于-1
         2.若n为正数 n^0=0,数不变，若n为负数有n^-1 需要计算n和-1的补码，然后进行异或运算，
         结果n变号并且为n的绝对值减1，再减去(-1)就是绝对值
           n>0 ==>-n = ~n+1
           n^-1=~n               1111 1111^0000 0000
                             1110 1011^1111 1111 = ~n
          -n = ~n+1; n^-1=~n
          n^-1-(-1) = ~n+1 =-n
        */
    }

    public static void main(String[] args) {

    }
}