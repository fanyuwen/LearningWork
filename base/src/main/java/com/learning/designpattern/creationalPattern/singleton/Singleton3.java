package com.learning.designpattern.creationalPattern.singleton;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 第三种单例模式：静态内部类实现
 */
public class Singleton3 {

    /**
     * @author fanyuwen
     * 同Singleton1 {@link Singleton1@Singleton1()}
     */
    private Singleton3() {
        if (Inner.obj != null) {
            throw new RuntimeException("不能通过反射创建该单例对象" + Singleton3.class);
        }
    }

    // 静态内部类
    private static class Inner {
        private static Singleton3 obj = new Singleton3();

        /**
         * 静态内部类实现即保证了对象的唯一性 也提高了性能， 但初学者较难理解
         */
        public static Singleton3 getInstance() {
            return Inner.obj;
        }
    }

    public static void main(String[] args) throws Exception {
        Singleton3 obj1 = Inner.getInstance();
        Singleton3 obj2 = Inner.getInstance();
        //比较两个对象是否是地址相同
        System.out.println(obj1 == obj2);
    }


}
