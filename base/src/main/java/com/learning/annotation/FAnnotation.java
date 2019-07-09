package com.learning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fanyuwen
 * @date 2019/7/10 0:54
 * 对java注解的完整介绍
 */
public class FAnnotation {

}

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Bairenjie {

    /**
     * 定义的方法名如果是value的话就不需要再使用的时候指定
     *
     * @return String
     */
    String value();

}