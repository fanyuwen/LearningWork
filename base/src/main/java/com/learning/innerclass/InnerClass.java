package com.learning.innerclass;

/**
 * @author fanyuwen
 * java基础内部类
 */
public class InnerClass {

    private String name;

    /**
     * 外围类对象也能访问成员内部类对象的私有属性
     * @param inner
     */
    void accessInnerClass(DynamicInner inner){
        System.out.println(inner.innerName);
    }

    public static void main(String[] args) {
        //创建成员内部类的方式,必须先要有外围类的对象
        InnerClass innerClass = new InnerClass();
        innerClass.new DynamicInner();
        //
    }

    /**
     * 成员内部类,自动有一个对外围类的引用
     * 不能创建静态属性
     */
    class DynamicInner {
//        private final InnerClass InnerClass.this; 编译器自动生成,所以能够访问外围类的成员(属性,方法)
        private String innerName;
        /**
         * 可以直接访问外围对象的私有属性和方法
         */
        void accessPrivateEnclosing(){
            System.out.println(name);
        }

        /**
         * 即使是参数也是一样的
         * @param innerClass param
         */
        void accessPrivate(InnerClass innerClass){
            System.out.println(innerClass.name);
        }
    }

    /**
     * 静态内部类,没有以用外围类的实例所以无法直接访问外围对象的实例成员(属性/方法)
     * 可以有静态属性
     */
    static class StaticInner{

    }
}