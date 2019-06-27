package com.learning.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author fanyuwen
 * @date 2019/6/27 10:58
 */
public class BucketTest extends BaseArrayTest {

    @Test
    public void testBucketSort() {
        Bucket bucket = new Bucket();
        System.out.println("排序前: " + Arrays.toString(test_arrays));
        int[] temp_array = test_arrays.clone();
        bucket.bucketSort(test_arrays);
        Arrays.sort(temp_array);
        System.out.println("排序后: " + Arrays.toString(test_arrays));
        System.out.println("排序后: " + Arrays.toString(temp_array));

        Assert.assertArrayEquals(test_arrays, temp_array);
    }

}