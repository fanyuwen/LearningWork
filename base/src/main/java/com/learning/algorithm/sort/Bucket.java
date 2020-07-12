package com.learning.algorithm.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author fanyuwen
 * @date 2019/6/27 8:22
 * 桶排序实现
 * 利用数字的每一位在数组中进行排序
 */
public class Bucket extends BaseSort {

    private static final int SCALE = 10,
            FILL_NUM = -1;

    public void bucketSort(int[] array) {
        int digit = findMax(array);
        //桶排序关键(二维数组),第二维是根据每一位十进制数字去排序的
        int i_i = 10;
        int i_i_i = 1;
        int high_digit = IntStream.rangeClosed(1, digit).map(i -> SCALE).reduce(1, (left, right) -> left * right);
        while (i_i_i < high_digit) {
            int[][] sortArray = initSortArray(array.length, null);
            //从最高位开始获取每一位的数字
            //遍历数组从最低位开始往高位遍历每一位的数
            for (int i = 0; i < array.length; i++) {
                int ele = array[i];
                int curr_digit = ele % i_i / i_i_i;//另一种实现是将数字/每次*10(从1开始) % 10
                sortArray[i][curr_digit] = ele;
            }
            //反向遍历,从桶的第二维数字开始遍历
            int index = 0;
            for (int i = 0; i < SCALE; i++) {
                for (int j = 0; j < sortArray.length; j++) {
                    if (sortArray[j][i] > FILL_NUM) {
                        array[index++] = sortArray[j][i];
                    }
                }
            }
            digit--;
            i_i *= 10;
            i_i_i *= 10;
        }
    }

    private static int[][] initSortArray(int len, int[][] sortArray) {
        if (sortArray == null || sortArray.length < len || sortArray[0].length < SCALE) {
            sortArray = new int[len][SCALE];
        }
        for (int[] array : sortArray) {
            Arrays.fill(array, FILL_NUM);
        }
        return sortArray;
    }

    /**
     * 从数组中找寻最大的那个数的位数
     *
     * @param array 带查找数组
     * @return 最大数的位数
     */
    private static int findMax(int[] array) {
        int max = 0;
        //找出数组中最大的数
        for (int ele : array) {
            if (ele > max) {
                max = ele;
            }
        }
        //计算该数拥有的位数
        int count = 0;
        while (max != 0) {
            count++;
            max /= 10;
        }

        return count;
    }

}

class RadixSort {
    // 获取x这个数的d位数上的数字
    // 比如获取123的1位数，结果返回3


    public int getDigit(int x, int d) {
        int a[] = {1, 1, 10, 100}; // 本实例中的最大数是百位数，所以只要到100就可以了
        return ((x / a[d]) % 10);
    }


    public void radixSort(int[] list, int begin, int end, int digit) {
        final int radix = 10; // 基数
        int i = 0, j = 0;
        int[] count = new int[radix]; // 存放各个桶的数据统计个数
        int[] bucket = new int[end - begin + 1];
        // 按照从低位到高位的顺序执行排序过程
        for (int d = 1; d <= digit; d++) {
            // 置空各个桶的数据统计
            for (i = 0; i < radix; i++) {
                count[i] = 0;
            }
            // 统计各个桶将要装入的数据个数
            for (i = begin; i <= end; i++) {
                j = getDigit(list[i], d);
                count[j]++;
            }
            // count[i]表示第i个桶的右边界索引
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 将数据依次装入桶中
            // 这里要从右向左扫描，保证排序稳定性
            for (i = end; i >= begin; i--) {
                j = getDigit(list[i], d);
                // 求出关键码的第k位的数字， 例如：576的第3位是5
                bucket[count[j] - 1] = list[i];
                // 放入对应的桶中，count[j]-1是第j个桶的右边界索引
                count[j]--; // 对应桶的装入数据索引减一
            }
            // 将已分配好的桶中数据再倒出来，此时已是对应当前位数有序的表
            for (i = begin, j = 0; i <= end; i++, j++) {
                list[i] = bucket[j];
            }
        }

    }


    public int[] sort(int[] list) {
        radixSort(list, 0, list.length - 1, 3);
        return list;
    }
    // 打印完整序列


    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "\t");

        }
        System.out.println();
    }

    void main() {
        int[] array = {50, 123, 543, 187, 49, 30, 0, 2, 11, 100};
        RadixSort radix = new RadixSort();
        System.out.print("排序前:\t\t");
        radix.printAll(array);
        radix.sort(array);
        System.out.print("排序后:\t\t");
        radix.printAll(array);
    }
}
