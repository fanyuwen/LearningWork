package com.learning.designpattern.singleton;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 第一种单例模式：饿汉式
 */
public class Singleton1 {

    // 类一加载对象就被初始化 保证了对象的唯一性 损耗了一部分性能
    private static Singleton1 obj = new Singleton1();

    private Singleton1() {}

    public static Singleton1 instance() {
        return obj;
    }
}
