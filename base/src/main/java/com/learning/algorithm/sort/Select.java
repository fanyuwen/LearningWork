package com.learning.algorithm.sort;

/**
 * @author fanyuwen
 * @date 2019/6/28 16:46
 * 选择排序实现
 */
public class Select extends BaseSort {

    /**
     * 简单选择排序实现
     *
     * @param array 待排序数组
     */
    public void sort(int[] array) {
        if (checkArray(array)) {
            for (int i = 0; i < array.length - 1; i++) {
                int dual = array.length - i - 1, compare_location = dual;//取最后需要排序的数字作为初次比较标准
                //循环的时候遍历每一个数与dual所在的数进行比较,将大的赋值给dual
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[dual]) {
                        dual = j;
                    }
                }
                //最后将dual所在的数交换到后面
                if (dual != compare_location) {
                    array[dual] += array[compare_location];
                    array[compare_location] = array[dual] - array[compare_location];
                    array[dual] = array[dual] - array[compare_location];
                }
            }
        }
    }

}