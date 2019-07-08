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

    /**
     * 快速插入排序,参照jdk DualPivotQuicksort类的实现
     * 思想就是在原有的以移动一个位置的排序改为移动两个
     * <p>
     * java.util.DualPivotQuicksort#sort(int[] a, int left, int right, boolean leftmost)
     *
     * @param array 待排序数组
     */
    public void fastSort(int[] array) {
        //[1, 4, 7, 9, 3, 2, 6, 5, 8]
        int length;
        if (checkArray(array) && (length = array.length) > 1) {
            //1.首先找到需要进行排序的那个下标,就是首个位置后面下标对应的数小于前一个数
            int index = 0;
            while (index + 1 < length && array[index] < array[++index]) ;
            if (index == length - 1)
                return;
            //index则为第一个小于前一个数的下标
            //left作为双标中左边的左边的标,index作为右边的标
            int first;
            for (first = index++; index < length; index += 2, first = index - 1) {
                int firstNum = array[first], secondNum = array[index];
                //取两者之间较大的数先进行排序
                if(firstNum < secondNum){
                    firstNum = secondNum;
                    secondNum = array[first];
                }
                //始终保持左边的数是大于右边的数(因为要先移动昨天的数)
                while (--first >= 0 && firstNum < array[first]) {
                    array[first + 2] = array[first];
                }
                array[++first + 1] = firstNum;
                //从右边的标开始标记
                while (--first >= 0 && secondNum < array[first]) {
                    array[first + 1] = array[first];
                }
                array[first + 1] = secondNum;
            }
            //最后因为可能数组最后一个元素没有被排序到的可能,所以还需要将数组最后一个元素进行处理
            first = length - 1;
            int firstNum = array[first];
            while (--first >= 0 && array[first] > firstNum) {
                array[first + 1] = array[first];
            }
            array[first + 1] = firstNum;
        }
    }
}
