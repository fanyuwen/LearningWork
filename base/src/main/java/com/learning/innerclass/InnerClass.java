package com.learning.innerclass;

/**
 * @author fanyuwen
 * java基础内部类
 */
public class InnerClass {

    private String name;

    /**
     * 外围类对象也能访问成员内部类对象的私有属性
     *
     * @param inner
     */
    void accessInnerClass(DynamicInner inner) {
        System.out.println(inner.dynamicInnerName);
    }

    /**
     * 外围类对象能够访问静态内部类对象的私有属性
     *
     * @param inner
     */
    void accessInnerClass(StaticInner inner) {
        System.out.println(inner.staticInnerName);
    }

    public static void main(String[] args) {
        //创建成员内部类的方式,必须先要有外围类的对象
        InnerClass innerClass = new InnerClass();
        innerClass.new DynamicInner();
        //可直接创建静态内部类
        InnerClass.StaticInner staticInner = new InnerClass.StaticInner();
    }

    /**
     * 成员内部类,自动有一个对外围类的引用
     * 不能创建静态属性(除非常量)
     */
    class DynamicInner {
        //        private final InnerClass InnerClass.this; 编译器自动生成,所以能够访问外围类的成员(属性,方法)
        private String dynamicInnerName;

        /**
         * 可以直接访问外围对象的私有属性和方法
         */
        void accessPrivateEnclosing() {
            System.out.println(name);
        }

        /**
         * 即使是参数也是一样的
         *
         * @param innerClass param
         */
        void accessPrivate(InnerClass innerClass) {
            System.out.println(innerClass.name);
        }
    }

    /**
     * 静态内部类,没有以用外围类的实例所以无法直接访问外围对象的实例成员(属性/方法)
     * 可以有静态属性
     */
    static class StaticInner {
        private String staticInnerName;

        //静态属性
        static int staticInnerAge;

        /**
         * 依然可以访问外围实例的私有属性
         *
         * @param innerClass 外围类实例
         */
        void accessPrivate(InnerClass innerClass) {
            System.out.println(innerClass.name);
        }
    }

    /**
     * 在非静态方法中声明的局部内部类
     */
    void dynamicLocalClass() {
        /*
         * 在非静态方法中声明的局部内部类可以直接访问外围类的属性
         * 不能有静态成员(除非常量)
         */
        class LocalInnerClass {
            private String dynamicLocalInnerName;

            /**
             * 直接访问外围实例的属性
             */
            void accessPrivateEnclosing() {
                System.out.println(name);
            }

            /**
             * 可以访问任何外围实例参数的属性
             * @param innerClass 外围类实例
             */
            void accessPrivate(InnerClass innerClass) {
                System.out.println(innerClass.name);
            }
        }
    }

    /**
     * 在静态方法中声明的局部内部类
     */
    static void staticLocalClass() {
        /*
         * 静态方法中声明的局部内部类不能直接访问外围类的属性
         * 不能有静态成员(除非常量)
         */
        class LocalInnerClass {
            private String staticLocalInnerName;

            /**
             * 可以访问任何外围实例参数的属性
             * @param innerClass 外围类实例
             */
            void accessPrivate(InnerClass innerClass) {
                System.out.println(innerClass.name);
            }
        }
    }

    /**
     * 在非静态方法中创建匿名内部类(成员属性创建的效果一致)
     */
    void dynamicAnonymousClass() {
        /*
         * 在非静态方法中创建匿名内部类可以直接访问外围类的属性
         */
        InnerClass innerClass = new InnerClass() {
            /**
             * 直接访问外围实例的属性
             */
            void accessPrivateEnclosing() {
                System.out.println(name);
            }

            /**
             * 可以访问任何外围实例参数的属性
             * @param innerClass 外围类实例
             */
            void accessPrivate(InnerClass innerClass) {
                System.out.println(innerClass.name);
            }
        };
    }

    /**
     * 在静态方法中创建匿名内部类(静态属性创建的效果一致)
     * 不能直接访问外围类的成员属性
     */
    static void staticAnonymousClass() {
        InnerClass innerClass = new InnerClass() {
            /**
             * 可以访问任何外围实例参数的属性
             * @param innerClass 外围类实例
             */
            void accessPrivate(InnerClass innerClass) {
                System.out.println(innerClass.name);
            }
        };
    }
}