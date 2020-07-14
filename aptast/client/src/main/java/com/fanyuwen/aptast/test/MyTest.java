package com.fanyuwen.aptast.test;

import com.fanyuwen.annotation.MyLocalVarAnnotation;

/**
 * @author fanyuwen
 * @date 2020/7/12 1:24
 */
public class MyTest {
    private String name;

    @MyLocalVarAnnotation
    void fun() {
        @MyLocalVarAnnotation
        int lizihan = 12;
    }
}