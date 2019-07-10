package com.learning.annotation;

import java.lang.annotation.*;

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
@Documented //表示该注解注解的元素自动生成javadoc文档
@Inherited //表示该注解标识的影响会被子类继承
@Retention(RetentionPolicy.RUNTIME)  //该注解存活级别(源码 类 运行时)
@Target(ElementType.METHOD) //标注该注解能够标注的元素
@interface Bairenjie {

    /**
     * 定义的方法名如果是value的话就不需要再使用的时候指定
     *
     * @return String
     */
    String value();
}