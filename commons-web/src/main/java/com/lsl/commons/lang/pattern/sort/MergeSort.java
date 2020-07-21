package com.lsl.commons.lang.pattern.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 15, 4, 3, 2, 10};
        System.out.println(Arrays.toString(sort(arr)));
    }

    public static int[] sort(int[] a) {
        if (a.length <= 1) {
            return a;
        }
        int num = a.length >> 1;
        int[] left = Arrays.copyOfRange(a, 0, num);
        int[] right = Arrays.copyOfRange(a, num, a.length);
        return merge(sort(left), sort(right));
    }

    /**
     * 合并数组并排序
     * 
     * @param a
     * @param b
     * @return
     */
    public static int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] result = new int[a.length + b.length]; // 申请额外空间保存归并之后数据

        while (i < a.length && j < b.length) { // 选取两个序列中的较小值放入新数组
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) { // 序列a中多余的元素移入新数组
            result[k++] = a[i++];
        }
        while (j < b.length) {// 序列b中多余的元素移入新数组
            result[k++] = b[j++];
        }
        return result;
    }
}