package com.learning.algorithm.sort;

/**
 * @author fanyuwen
 * @date 2019/6/26 19:38
 * 快速排序
 */
public class Fast extends BaseSort {

    /**
     * 经典快速排序(单轴快速排序)
     *
     * @param array 待排序数组
     */
    public void classicsSort(int[] array) {
        if (checkArray(array)) {
            fastSort(array, 0, array.length - 1);
        }
    }

    /**
     * 变种快速排序
     *
     * @param array 待排序数组
     */
    public void variantSort(int[] array) {
        if (checkArray(array)) {
            variantFastSort(array, 0, array.length - 1);
        }
    }

    /**
     * 变种快速排序实现
     * @param array 待排序数组
     * @param start 起始下标
     * @param end 结束下标
     */
    private void variantFastSort(int[] array, int start, int end) {
        if (checkRange(array.length, start, end)) {
            //通过移动数组来实现,思想还是将数组一分为二
            //还是以第一个元素为基准
            int first = start,
                    index = first + 1,
                    dual = array[first];
            while (index < end + 1) {
                if (array[index] <= dual) {
                    //index索引满足条件的先排到前面,
                    //再将然后轴后面第一位数复制给index索引处,
                    //最后将轴复制到后一位(这种顺序的必要性是因为当index=first+1的时候保证正确的复制)
                    int t_index = array[index];
                    int temp = array[first + 1];

                    array[first] = t_index;
                    array[index] = temp;
                    array[++first] = dual;
                }
                index++;
            }

            variantFastSort(array, start, first - 1);
            variantFastSort(array, first + 1, end);
        }
    }

    /**
     * 经典快速排序实现
     *
     * @param array 待排序数组
     * @param start 开始下标
     * @param end   结束下标
     */
    private void fastSort(int[] array, int start, int end) {
        if (checkRange(array.length, start, end)) {
            //这是一个交换标准(默认选用start索引对应的那个数作为标准)
            int dual = array[start];

            int first = start,//头索引
                    last = end;//尾索引

            while (first < last) {
                //先从尾索引开始往前遍历直到找到一个比标准小的数
                while (array[last] > dual && last > first) {
                    last--;
                }
                //往后遍历头索引知道找到一个比标准大的数
                while (array[first] <= dual && last > first) {
                    first++;
                }
                if (last > first) {
                    //对这两个下标的数进行交换
                    array[last] += array[first];
                    array[first] = array[last] - array[first];
                    array[last] = array[last] - array[first];
                }
            }
            //当前first==last 就是中轴
            //将标准数交换到当前的轴的位置
            array[start] = array[last];
            array[last] = dual;

            //将当前数组分成了两组,分别是小于等于dual的和大于dual的,采用递归的方式
            fastSort(array, start, last - 1);//对前一半进行递归排序
            fastSort(array, last + 1, end);//对后一半进行递归排序
        }
    }

}
