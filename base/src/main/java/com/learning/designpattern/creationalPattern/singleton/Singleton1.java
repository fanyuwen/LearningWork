package com.learning.designpattern.creationalPattern.singleton;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 第一种单例模式：饿汉式
 */
public class Singleton1 {

    // 类一加载对象就被初始化 保证了对象的唯一性 损耗了一部分性能
    private static Singleton1 obj = new Singleton1();

    /**
     * @author fanyuwen
     * 因为无法保证反射创建对象,所以需要在构造函数里添加如下代码
     * 保证即使是反射也无法创建该单例对象
     */
    private Singleton1() {
        if (obj != null) {
            throw new RuntimeException("不能通过反射创建该单例对象" + Singleton1.class);
        }
    }

    public static Singleton1 instance() {
        return obj;
    }
}
