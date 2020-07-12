package com.fanyuwen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来测试java annotation processor tool 和 抽象语法树
 *
 * @author fanyuwen
 * @date 2020-07-12
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface TestAnnotation {
}
