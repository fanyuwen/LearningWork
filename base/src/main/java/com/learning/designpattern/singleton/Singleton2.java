package com.learning.designpattern.singleton;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 第二种单例模式：懒汉式
 */
public class Singleton2 {

    // 类加载并不会初始化
    private static Singleton2 obj = null;

    /**
     * @author fanyuwen
     * 这里有一个弊端,因为是延迟初始化,所以没有办法禁止在初始化之前利用反射创建对象
     * 同Singleton1 {@link Singleton1@Singleton1()}
     */
    private Singleton2() {
        if (obj != null) {
            throw new RuntimeException("不能通过反射创建该单例对象" + Singleton2.class);
        }
    }

    /**
     * 懒汉式需要double check 保证对象的唯一性 代码过于复杂 不建议使用
     */
    public static Singleton2 instance() {
        // 第一次判断obj是否初始化
        if (obj == null) {
            // 第一次未初始化 需要锁住初始化代码块
            synchronized (Singleton2.class) {
                // 第二次判断保证了对象的唯一性
                if (obj == null) {
                    obj = new Singleton2();
                }
            }
        }
        return obj;
    }
}
