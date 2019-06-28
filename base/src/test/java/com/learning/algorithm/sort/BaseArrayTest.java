package com.learning.algorithm.sort;

import org.junit.Before;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author fanyuwen
 * @date 2019/6/27 11:00
 */
public class BaseArrayTest {
    private static final Random r = new Random();
    static int[] test_arrays;

    @Before
    public void fun() {
        test_arrays = IntStream.rangeClosed(1, 20)
                .map(i -> r.nextInt(1000))
                .toArray();
//        test_arrays = new int[]{4, 51, 25, 28, 65, 62, 66, 41, 33, 28};
    }


}
