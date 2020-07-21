package com.lsl.commons.lang.pattern.sort;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 8, 23, 10, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// 控制比较次数
            for (int j = 0; j < arr.length - i - 1; j++) {// 每次交换顺序
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 通过临时变量交换数组array的i和j位置的数据
     * 
     * @param arr
     *            数组
     * @param m
     *            下标m
     * @param n
     *            下标n
     */
    static void swap(int[] arr, int m, int n) {
        int temp = arr[n];
        arr[n] = arr[m];
        arr[m] = temp;
    }

    /**
     * 通过算术法交换数组array的i和j位置的数据（有可能溢出）
     * 
     * @param array
     *            数组
     * @param i
     *            下标i
     * @param j
     *            下标j
     */
    public static void swapByArithmetic(int[] array, int i, int j) {
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];
        array[i] = array[i] - array[j];
    }

    /**
     * 通过位运算法交换数组array的i和j位置的数据
     * 
     * @param array
     *            数组
     * @param i
     *            下标i
     * @param j
     *            下标j
     */
    public static void swapByBitOperation(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j]; // array[i]^array[j]^array[j]=array[i]
        array[i] = array[i] ^ array[j]; // array[i]^array[j]^array[i]=array[j]
    }
}
