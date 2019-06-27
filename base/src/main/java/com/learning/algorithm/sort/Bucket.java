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
                int curr_digit = ele % i_i / i_i_i;
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
