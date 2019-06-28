package com.learning.algorithm.sort;

/**
 * @author fanyuwen
 * @date 2019/6/27 23:55
 * 插入排序,代码实现
 */
public class Insert extends BaseSort {

    /**
     * 经典插入排序实现
     *
     * @param array 待排序数组
     */
    public void sort(int[] array) {
        if (checkArray(array) && array.length > 1) {
            for (int i = 1, j = i; i < array.length; j = ++i) {
                int num = array[i];
                while (--j > -1 && array[j] > num) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = num;
            }
        }
    }
}
