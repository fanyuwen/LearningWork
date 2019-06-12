package com.learning.designpattern.singleton;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 第三种单例模式：静态内部类实现
 */
public class Singleton3 {

    private Singleton3() {}

    // 静态内部类
    private static class inner {
        private static Singleton3 obj = new Singleton3();

        /**
         * 静态内部类实现即保证了对象的唯一性 也提高了性能， 但初学者较难理解
         */
        public static Singleton3 getInstance() {
            return inner.obj;
        }
    }

    public static void main(String[] args) {
        Singleton3 obj1 = Singleton3.inner.getInstance();
        Singleton3 obj2 = Singleton3.inner.getInstance();
        // 比较两个对象是否是地址相同
        System.out.println(obj1 == obj2);
    }


}
