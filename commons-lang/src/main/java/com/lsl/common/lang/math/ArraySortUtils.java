package com.lsl.common.lang.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Description
 * @Author yangcao
 * @Date 2019/10/15 9:23
 * @Version v1.0
 */
public class ArraySortUtils {

    /**
     * @return
     * @Description 1.int temp=a,a=b,b=temp 2.a=a+b b=a-b a=a-b =a+b-a BUG 3.b^b=a n^m^m=n
     * @Date 2019/10/16 10:29
     * @Param
     **/
    public static void swapByBit(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];// 需要判断i == j 拦截
        array[i] = array[i] ^ array[j];
    }

    public static void swapByTemp(int[] array, int i, int j) {
        int var = array[i];
        array[j] = array[i];
        array[i] = var;
    }

    public static void swapByArith(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        array[i] = array[i] + array[j];
        array[j] = array[i] - array[j];// 需要判断i == j 拦截
        array[i] = array[i] - array[j];
    }

    /**
     * @return
     * @Description 冒泡排序算法
     * @Date 2019/10/16 10:27
     * @Param
     **/
    public static void bubble(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {// 外层控制次数
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapByBit(array, j, j + 1);
                }
            }
        }
    }

    /**
     * @Description 插入基准值法 基于插入排序 1.找出基准 2.分别向左和向右找出，小于和大于基准值得下标，然后交换数据 3.将基准值和最左（右侧）的最后一个（第一个）与基准值交换，
     *              得到基准值比左侧数据（大），比右侧数据（小） 4.以基准值为分界点拆分分治，最终拆分为数据长度为1，退出不再拆分
     *
     *
     *              [137, 31, 163, 7, 176, 15, 71, 104, 8, 157] 回合开始==>开始[0]：137，结束[9]：157,基准排序后: [71, 31, 8, 7, 104,
     *              15, 137, 176, 163, 157]，基准位置[0==>6]：137 回合开始==>开始[0]：71，结束[5]：15,基准排序后: [15, 31, 8, 7, 71, 104, 137,
     *              176, 163, 157]，基准位置[0==>4]：71 回合开始==>开始[0]：15，结束[3]：7,基准排序后: [8, 7, 15, 31, 71, 104, 137, 176, 163,
     *              157]，基准位置[0==>2]：15 回合开始==>开始[0]：8，结束[1]：7,基准排序后: [7, 8, 15, 31, 71, 104, 137, 176, 163,
     *              157]，基准位置[0==>1]：8 回合开始==>开始[7]：176，结束[9]：157,基准排序后: [7, 8, 15, 31, 71, 104, 137, 157, 163,
     *              176]，基准位置[7==>9]：176 回合开始==>开始[7]：157，结束[8]：163,基准排序后: [7, 8, 15, 31, 71, 104, 137, 157, 163,
     *              176]，基准位置[7==>7]：157
     *
     * @Date 2019/10/18 10:15
     * @Param arr 待排数组
     * @Param low 开始下标位置
     * @Param high 结束下标位置
     **/
    public static void quickByBaseValue(int arr[], int low, int high) {

        // 退出条件
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        System.out.print("回合开始==>开始[" + low + "]：" + arr[low] + "，结束[" + high + "]：" + arr[high] + ",");
        int leftBaseIndex = low;
        int right = high;
        int baseValue = arr[leftBaseIndex];
        // 1.取左边值为基准值 用于填不到left位置 （每一轮结束需要和left最小值交换数据）

        while (leftBaseIndex < right) {// 2.
            while (leftBaseIndex < right && arr[right] >= baseValue) {// 向左搜索一个小于基准值数据就停止
                right--;
            }
            while (leftBaseIndex < right && arr[leftBaseIndex] <= baseValue) {// 向右搜索一个大于基准值就停止
                leftBaseIndex++;
            }
            swapByBit(arr, leftBaseIndex, right);// 3.基准值和左侧最后一个值交换
        }

        swapByBit(arr, low, leftBaseIndex);

        System.out.println(
            "基准排序后: " + Arrays.toString(arr) + "，基准位置[" + low + "==>" + leftBaseIndex + "]：" + arr[leftBaseIndex]);
        quickByBaseValue(arr, low, leftBaseIndex - 1);
        quickByBaseValue(arr, leftBaseIndex + 1, high);
    }

    /**
     * 挖坑法||临时变量法 right覆盖left left覆盖right 剩余left使用baseValue填充
     * 
     * @Description 挖坑法||临时变量法
     * @Date 2019/10/18 10:15
     * @Param
     **/
    public static void quickByTempSpace(int arr[], int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int baseValue = arr[left]; // 挖坑1：保存基准的值

        while (left < right) {
            while (left < right && arr[right] >= baseValue) {
                right--;
            }
            arr[left] = arr[right]; // 坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            while (left < right && arr[left] <= baseValue) {
                left++;
            }
            arr[right] = arr[left]; // 坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
        }
        arr[left] = baseValue; // 基准值填充到最后一个LEFT位置，准备分治递归快排

        quickByTempSpace(arr, low, left - 1);
        quickByTempSpace(arr, left + 1, high);
    }

    /**
     * 直接插入排序的基本思想是：将数组中的所有元素依次跟前面已经排好的元素相比较， 如果选择的元素比已排序的元素小，则交换，直到已排序全部元素都比较过为止
     * 
     * @Description 插入排序
     * @Date 2019/10/18 12:36
     * @Param
     **/
    public static void insertElementWithShift(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i]; // 先取出待插入数据保存，因为向后移位过程中会把覆盖掉待插入数
            // while (j >= 0 && arr[j] > arr[i]) { // 如果待是比待插入数据大，就后移 {1,3,4,2}; arr[i]被修改是错误的
            while (j >= 0 && arr[j] > temp) { // 如果待是比待插入数据大，就后移
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp; // 找到比待插入数据小的位置，将待插入数据插入
        }
    }

    public static void insertElementWithSwap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[j + 1]) {
                // 只要大就交换操作
                swapByBit(arr, j, j + 1);
                j--;
            }
        }
    }

    /**
     * @Description Shell排序 1）当序列的个数比较少时，直接插入排序效率高；这个好理解，个数比较少，那么插入的次数也就少了， 博主就说：“恩，这个发现不难，却也需要细心”。
     *              2）如果序列本身就是基本有序，那么直接插入排序效率高 先将整个待排记录序列分割成为若干个子序列，然后分别进行直接插入排序。 待整个序列中的记录“基本有序”时，再对全体记录进行一次直接插入排序。
     *              先将整个待排记录序列分割成为若干个子序列， 然后分别进行直接插入排序。待整个序列中的记录“基本有序”时，再对全体记录进行一次直接插入排序。
     *              https://blog.51cto.com/13733462/2114341
     * @Date 2019/10/18 13:30
     * @Param
     * @return
     **/
    public static void shell(int[] array) {
        int gap = array.length;
        while (true) {
            gap /= 2; // 增量每次减半
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {// 这个循环里其实就是一个插入排序
                    int temp = array[j];
                    int k = j - gap;
                    while (k >= 0 && array[k] > temp) {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    array[k + gap] = temp;
                }
            }
            if (gap == 1)
                break;
        }
    }

    /**
     * 归并排序
     * 
     * @param array
     * @return
     */
    public static int[] merge(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int num = array.length >> 1;
        int[] left = Arrays.copyOfRange(array, 0, num);
        int[] right = Arrays.copyOfRange(array, num, array.length);
        return mergeSortedArray(merge(left), merge(right));
    }

    /**
     * 合并2个有序数列形成新的有序数列 1.带合并数组是有序 2.返回新数组消耗空间
     * 
     * @param var1
     * @param var2
     * @return
     */
    public static int[] mergeSortedArray(int[] var1, int[] var2) {
        if (var1 == null) {
            return var2;
        }
        if (var2 == null) {
            return var1;
        }

        int L1 = 0;
        int L2 = 0;
        int L12 = 0;

        // 申请额外空间保存归并之后数据
        int[] result = new int[var1.length + var2.length];

        // 选取两个序列中的较小值放入新数组
        while (L1 < var1.length && L2 < var2.length) {
            if (var1[L1] <= var2[L2]) {
                result[L12++] = var1[L1++];
            } else {
                result[L12++] = var2[L2++];
            }
        }

        while (L1 < var1.length) { // 序列a中多余的元素移入新数组
            result[L12++] = var1[L1++];
        }
        while (L2 < var2.length) {// 序列b中多余的元素移入新数组
            result[L12++] = var2[L2++];
        }
        return result;
    }

    /**
     * 基数排序 1.取得数组中的最大数,并取得位数; 2.arr为原始数组,从最低位开始取每个位组成radix数组; 3.对radix进行计数排序(利用计数排序适用于小范围数的特点); 4.后一次实际与前一次有序数基础在排序
     * 
     * @param array
     */
    public static void baseSort06(int[] array) {

        if (array == null || array.length < 0) {
            return;
        }
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("max, " + max);

        int maxDigit = 0;
        while (max != 0) {
            max = max / 10;
            maxDigit++;
        }

        System.out.println("maxDigit, " + maxDigit);

        int[][] buckets = new int[10][array.length];// i(0-9铜下标) j =子桶长度

        int base = 10;
        // 从低位到高位，对每一位遍历，将所有元素分配到桶中
        for (int i = 0; i < maxDigit; i++) {
            int[] subBucketLength = new int[10]; // 存储各个桶中存储元素的数量

            // 收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            for (int j = 0; j < array.length; j++) {
                int bucketIndex = (array[j] % base) / (base / 10);// 对应位的0-9
                buckets[bucketIndex][subBucketLength[bucketIndex]] = array[j];
                subBucketLength[bucketIndex]++;// 下标自增
            }

            int k = 0;
            // 收集：将不同桶里数据挨个捞出来,为下一轮高位排序做准备,由于靠近桶底的元素排名靠前,因此从桶底先捞
            for (int l = 0; l < buckets.length; l++) {// 分组桶
                for (int m = 0; m < subBucketLength[l]; m++) {// 子桶长度
                    array[k++] = buckets[l][m];
                }
            }
            System.out.println("Sorting: " + Arrays.toString(array));
            base *= 10;
        }
    }

    /**
     * 堆排序 本质属于选择排序 选中大顶堆的最大元素 然后将最大元素和末尾元素交换 a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆; b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素， 反复执行调整+交换步骤，直到整个序列有序。
     * 
     * @param arr
     */
    public static void heap(int[] arr) {
        // 1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            buildHeap(arr, i, arr.length);
        }
        // 2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swapByBit(arr, 0, j);// 将堆顶元素与末尾元素进行交换
            buildHeap(arr, 0, j);// 重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上） 大顶堆满足 arr[i]>arr[2i+1] arr[i]>arr[2i+2]
     * 
     * @param arr
     * @param i
     * @param length
     */
    public static void buildHeap(int[] arr, int i, int length) {
        int temp = arr[i];// 先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {// 从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {// 如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;// 将temp值放到最终的位置
    }

    // 二维数组几何意义
    // 二维数组自增操作

    // 高效排序算法在于数据维度拆分直到简单排列为止，含有分治思想 基数 归并（分解） 快排（分解） Shell

    public static int[] initRandomArray(int length, int bound) {
        List<Integer> list = new ArrayList();
        if (length > bound) {
            throw new RuntimeException("初始化随机数组参数错误，长度大于边界！");
        }
        if (length > (bound - 100)) {
            bound = bound + 100;
        }
        Random r = new Random();
        while (list.size() != length) {
            int value = r.nextInt(bound);
            if (!list.contains(value)) {
                list.add(value);
            }
        }
        return list.stream().mapToInt(v -> v.intValue()).toArray();
    }

    public static void main(String[] args) {
        // int[] arr = initRandomArray(10, 100);
        // System.out.println(Arrays.toString(arr));
        // Arrays.stream(initRandomArray(10, 100)).forEach(System.out::println);
        // quickByBaseValue(arr, 0, arr.length - 1);
        // insertElementWithSwap(arr);
        // merge(arr);
        // baseSort06(arr);
        // shell(arr);
        // heap(arr);

        // int[] var2 = ArrayUtils.initRandomArray(10, 100);
        // System.out.println(Arrays.toString(var2));
        // insertElementWithShif t(var2);
        // System.out.println(Arrays.toString(var2));

        int[] var3 = ArraySortUtils.initRandomArray(10, 100);
        System.out.println(Arrays.toString(var3));
        ArraySortUtils.quickByBaseValue(var3, 0, var3.length - 1);// low=1 放弃对第一个元素排序
        System.out.println(Arrays.toString(var3));

    }
}
