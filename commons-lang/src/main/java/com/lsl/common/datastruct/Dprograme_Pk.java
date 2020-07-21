package com.lsl.common.datastruct;

import java.util.Arrays;

class Dprograme_Pk {

    // 第一、递归函数的定义
    // 第二、数据结构
    // 函数定义：
    // F（n,C）递归函数定义：将n个物品放入容量为C的背包，使得价值最大。
    // 这里要注意一下，第二个参数一定是剩余容量。我们通过使用剩余容量来控制价值。
    // F（i，c） = F（i-1,c）
    // = v(i) + F(i-1 , c-w(i))
    // 状态转移方程：
    // F（i，c） = max( F(i-1 , c) , v(i) + F(i-1 , c-w(i) ) )
    // 即，当前价值的最大值为，不放入第i个物品(对应剩余容量为c)
    // 和放入第i个物品（对应剩余容量为C-w(i)）两种情况的最大值。

    public static int knapsack01(int[] w, int[] v, int c) {
        // w为0-~n-1物品对应价值
        // v为0~n-1物品对应重量。
        // C为背包容量
        int n = w.length;
        if (n == 0) {
            return 0;
        }

        // 动态规划记忆数组(表格)

        int[][] dp = new int[n][c];
        // 初始化第一行。
        for (int j = 0; j < c; j++) {
            // 背包能放下W[0]
            dp[0][j] = (j >= w[0] ? v[0] : 0);
        }
        // 0行
        int[] value0 = dp[0];

        System.out.println("===" + Arrays.toString(value0));

        // n w v 0 1 2 3 4 5 6 7 8
        // 1 1 10 0 10 10
        // 2 4 3
        // 3 2 5
        // 4 8 8

        // 21
        System.out.println("w==>" + Arrays.toString(w));
        System.out.println("v==>" + Arrays.toString(v));
        // 0-3
        for (int i = 1; i < n; i++) {// v.len w.len 前i个
            for (int j = 0; j < c; j++) {// 14 0-13
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {// >8
                    int backStepValue = v[i] + dp[i][j - w[i]];
                    if (j >= backStepValue && backStepValue > dp[i][j]) {// >8
                        dp[i][j] = backStepValue;
                    }
                }
            }
            System.out.println("===" + Arrays.toString(dp[i]));
        }

        for (int i = 0; i < n; i++) {
            System.out.println("行==>" + i + "<==" + Arrays.toString(dp[i]));
        }

        return dp[n - 1][c - 1];
    }

    /**
     * 使用动态规则来求物品的最大价值
     *
     * @param weights
     *            物品信息
     * @param values
     *            最大价值
     * @param maxNum
     *            最大数量
     * @param maxWeight
     *            最大重量
     * @return 求最大价值
     */
    public static int dynamicProgammingPackageMaxValue(int[] weights, int[] values, int maxNum, int maxWeight) {
        // 用于标识当前层的最大值问题
        int[][] valueStatus = new int[maxNum][maxWeight + 1];

        // 初始化
        for (int i = 0; i < maxNum; i++) {
            for (int j = 0; j <= maxWeight; j++) {
                valueStatus[i][j] = -1;
            }
        }

        // 初始化第一个物品
        valueStatus[0][0] = 0;
        valueStatus[0][weights[0]] = values[0];

        // 使用动态规划来进行求解
        for (int i = 1; i < maxNum; i++) {
            // 求解上一层的背包
            for (int j = 0; j <= maxWeight; j++) {
                // 如果上一层设置了值，在当前物品肯定也会被放入
                if (valueStatus[i - 1][j] >= 0) {
                    valueStatus[i][j] = valueStatus[i - 1][j];
                }
            }
            // 求解当前层的问题,背包重量不能超过最大限制
            for (int j = 0; j <= maxWeight - weights[i]; j++) {
                if (valueStatus[i - 1][j] >= 0) {
                    // 首先检查当前节点是否已经被设置过值，设置过，则需要比较大小
                    int newValue = valueStatus[i - 1][j] + values[i];
                    System.out.println("当前的value:" + newValue);
                    if (newValue > valueStatus[i][j + weights[i]]) {
                        valueStatus[i][j + weights[i]] = newValue;
                    }
                }
            }
        }

        int resultValue = -1;

        for (int i = 0; i <= maxWeight; i++) {
            if (valueStatus[maxNum - 1][i] > resultValue) {
                resultValue = valueStatus[maxNum - 1][i];
            }
        }

        return resultValue;
    }

    public static void main(String[] args) {
        int[] w = new int[] {1, 4, 2, 8};
        int[] v = new int[] {10, 3, 5, 6};
        int maxValue = dynamicProgammingPackageMaxValue(w, v, v.length, 14);
        System.out.println(maxValue);

    }
}
