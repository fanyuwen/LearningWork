package com.learning.algorithm.sort;

import java.util.Random;

/**
 * @author fanyuwen
 * @date 2019/6/26 20:25
 * 基础排序公用方法封装
 */
abstract class BaseSort {

    /**
     * 校验int数组是否合法
     *
     * @param array int数组
     * @return 如果数组不为空并且长度大于0返回true, 否则返回false
     * @author fanyuwen
     */
    static boolean checkArray(int[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 校验数组下标索引范围是否合法
     *
     * @param len   数组长度
     * @param start 数组起始下标
     * @param end   数组结束下标
     * @return 如果 start < 0 or start >= len or end < 0 or end >= len or start > end返回true
     * 否则返回false
     * idea有条件冗余校验(关键部分表达式的结果可以决定整体的结果时,其余的表达式就是冗余的)
     * @author fanyuwen
     */
    static boolean checkRange(int len, int start, int end) {
        return !(start < 0 || end < 0 || end >= len || start >= end);
    }
}
