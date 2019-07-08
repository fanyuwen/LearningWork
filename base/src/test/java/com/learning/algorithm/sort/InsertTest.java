package com.learning.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author fanyuwen
 * @date 2019/6/28 16:31
 */
public class InsertTest extends BaseArrayTest {

    @Test
    public void testInsert() {
        Insert insert = new Insert();
        System.out.println("排序前: " + Arrays.toString(test_arrays));
        int[] temp_array = test_arrays.clone();
        insert.sort(test_arrays);
        Arrays.sort(temp_array);
        System.out.println("排序后: " + Arrays.toString(test_arrays));
        System.out.println("排序后: " + Arrays.toString(temp_array));

        Assert.assertArrayEquals(test_arrays, temp_array);
    }

    @Test
    public void testfastInsert() {
        Insert insert = new Insert();
        System.out.println("排序前: " + Arrays.toString(test_arrays));
        int[] temp_array = test_arrays.clone();
        insert.fastSort(test_arrays);
        Arrays.sort(temp_array);
        System.out.println("排序后: " + Arrays.toString(test_arrays));
        System.out.println("排序后: " + Arrays.toString(temp_array));

        Assert.assertArrayEquals(test_arrays, temp_array);
    }
}
