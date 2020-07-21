package com.lsl.commons.lang.pattern.sort;

import java.util.Arrays;

/**
 * 快速排序 ①. 从数列中挑出一个元素，称为”基准”（pivot）。 ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。
 * 在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。 ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 递归到最底部时，数列的大小是零或一，也就是已经排序好了。这个算法一定会结束，因为在每次的迭代（iteration）中， 它至少会把一个元素摆到它最后的位置去。
 * <p>
 * <p>
 * dealPivot
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }

    /**
     * @param arr
     * @param left
     *            左指针
     * @param right
     *            右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        // 取每个序列的第一个值作为基准值
        int pivotkey = arr[left];
        while (left < right) {
            // 从序列的右边开始往左遍历，直到找到小于基准值的元素      
            while (left < right && arr[right] >= pivotkey) {
                right--;
            }
            // 将元素直接赋予给左边第一个，即pivotkey所在的位置   
            arr[left] = arr[right];
            // 从序列的左边边开始往右遍历，直到找到大于基准值的元素 
            while (left < right && arr[left] <= pivotkey) {
                left++;
            }
            // 此时的a[high]<pivotkey,已经被赋予到左边，所以可以将元素赋予给a[high]            
            arr[right] = arr[left];
        }
        // 最后的low是基准值所在的位置 
        arr[left] = pivotkey;
        return left;
    }
}