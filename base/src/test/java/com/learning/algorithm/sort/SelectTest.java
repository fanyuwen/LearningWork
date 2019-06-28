package com.learning.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author fanyuwen
 * @date 2019/6/28 19:18
 */
public class SelectTest extends BaseArrayTest{

    @Test
    public void selectTest(){
        Select select = new Select();
        System.out.println("排序前: " + Arrays.toString(test_arrays));
        int[] temp_array = test_arrays.clone();
        select.sort(test_arrays);
        Arrays.sort(temp_array);
        System.out.println("排序后: " + Arrays.toString(test_arrays));
        System.out.println("排序后: " + Arrays.toString(temp_array));

        Assert.assertArrayEquals(test_arrays, temp_array);
    }

}