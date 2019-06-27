package com.learning.algorithm.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author fanyuwen
 * @date 2019/6/27 1:28
 * 快速排序测试用例
 */
public class FastTest {
    private static final Random r = new Random();
    private static int[] test_arrays;

    @Before
    public void fun() {
        test_arrays = IntStream.rangeClosed(1, 20)
                .map(i -> r.nextInt(30))
                .toArray();
    }

    @Test
    public void testClassicsSort() {
        Fast fast = new Fast();
        System.out.println("排序前: " + Arrays.toString(test_arrays));
        int[] temp_array = test_arrays.clone();
        fast.variantSort(test_arrays);
        Arrays.sort(temp_array);
        System.out.println("排序后: " + Arrays.toString(test_arrays));
        System.out.println("排序后: " + Arrays.toString(temp_array));

        Assert.assertArrayEquals(test_arrays, test_arrays);
    }


}
