package com.lsl.dsp.number;

public class NumberUtil {
    /**
     * 加减算法
     * 
     * @param length
     * @param numStr
     * @return
     */
    public static String formatBinaryString(int length, String numStr) {
        StringBuffer sb = new StringBuffer("00000000000000000000000000000000");
        if (numStr == null) {
            return sb.toString();
        } else {
            if (numStr.length() == Integer.SIZE) {
                return numStr;
            } else {
                sb.delete(0, numStr.length()).append(numStr);
                return sb.toString();
            }
        }
    }

    public static void main(String[] args) {

        // System.out.println(converOppoSiteCode(-102));
        // System.out.println(Integer.toBinaryString(-((~10) + 1)));// n=-((~n)+1)
        // System.out.println(Integer.toBinaryString(~(-(-10)) + 1)); // n=~(-n)+1
        System.out.println(add(1, 2)); // n<0

    }

    /**
     * n>0 -n=~n+1=~(n-1) ~n=-(n+1)，比如：~3=-4
     * 
     * @param n
     *            补码
     * @return 原码
     */
    public static int converGenericCode(int n) {
        int r = n;
        if (n < 0) {
            r = -n;
        }
        return r;
    }

    /**
     * 
     * @param n
     *            补码
     * @return 反码 >>> >> ^ a同或b=~(a^b)
     */
    public static int converOppoSiteCode(int n) {// -3=~3+1 -4=~3
        int r = n;
        if (n < 0) {
            r = n - 1;
        }
        return r;
    }

    /**
     * 加法：a+b 由a^b可得按位相加后没有进位的和； 由a&b可得可以产生进位的地方； 由(a&b)<<1得到进位后的值。 那么 按位相加后原位和+进位和 就是加法的和了，而 a^b + (a&b)<<1 相当于把 +
     * 两边再代入上述三步进行加法计算。直到进位和为0说明没有进位了则此时原位和即所求和。
     * 
     * @param a
     *            00010110
     * @param b
     *            00110101 ^ 00100011 sum & 00010100 <<1 00101000 forward==>0
     * @return
     */
    public static int add(int a, int b) {
        // int r = a;
        // int sum = a ^ b;// 得到原位和
        // int forward = (a & b) << 1;// 得到进位和
        // if (forward != 0) {// 若进位和不为0，则递归求原位和+进位和
        // r = add(sum, forward);
        // } else {
        // r = sum;// 若进位和为0，则此时原位和为所求和
        // }
        System.out.println(a + "+" + b + "=");
        int sum = a ^ b;
        int forward = (a & b) << 1;
        while (forward != 0) {
            // int tempSum = sum;
            // sum = sum^forward;
            // forward = (tempSum & forward) << 1;
            int tempSum = sum ^ forward;
            int tempForward = (sum & forward) << 1;
            sum = tempSum;
            forward = tempForward;

        }
        return sum;
    }

    /**
     * 减法：a-b 由-b=+(-b)，~（b-1）=-b可得a-b=a+（-b）=a+(~(b-1))。把减法转化为加法即可。
     */
    public static int minus(int a, int b) {
        int B = ~(b - 1);
        return add(a, B);
    }

    /**
     * 乘法：a*b 先来看一下二进制乘法是怎么做的 1011 1010 -------- 10110 (1011<<1,相当于乘以0010) 1011000 (1011<<3,相当于乘以1000) -------- 1101110
     * 可以看到，二进制乘法的原理是：从乘数的低位到高位，遇到1并且这个1在乘数的右起第i（i从0开始数）位， 那么就把被乘数左移i位得到 temp_i 。直到乘数中的1遍历完后，把根据各位1而得到的被乘数的左移值们 temp_i
     * 相加起来即得乘法结果。那么根据这个原理，可以得到实现代码：这里要点为： 用i记录当前遍历的乘数位,当前位为1则被乘数左移i位并加到和中，同时i++处理下一位；
     * 为0则乘数右移，i++，处理下一位......直到乘数==0说明乘数中的1遍历完了。此时把和返回即可。
     */
    public static int multi(int a, int b) {
        int i = 0;
        int res = 0;
        while (b != 0) {// 乘数为0则结束
            // 处理乘数当前位
            if ((b & 1) == 1) {
                res += (a << i);
                b = b >> 1;
                ++i;// i记录当前位是第几位
            } else {
                b = b >> 1;
                ++i;
            }
        }
        return res;
    }

    /**
     * 除法：a/b 除法的意义就在于：求a可以由多少个b组成。那么由此我们可得除法的实现：求a能减去多少个b， 做减法的次数就是除法的商。
     */
    public static int sub(int a, int b) {
        int res = -1;
        if (a < b) {
            return 0;
        } else {
            res = sub(minus(a, b), b) + 1;
        }
        return res;
    }
}
