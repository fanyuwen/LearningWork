package com.learning.enums;

/**
 * @author fanyuwen
 * 枚举学习
 */
public class FEnum {

}

//枚举无法继承也无法被继承
enum A {
    a1, a2;

    private int age;
    private static String name;

    //枚举构造函数默认私有
    //构造函数(动态代码块)无法访问静态属性
    A() {
    }

    //内部类规则与正常的类一致
    class DynamicInnerA{}
    static class StaticInnerA{}
    //内部枚举默认都是静态的
    static enum C{}
}

enum B {
    b1 {
        @Override
        void fun() {
            System.out.println("b1");
        }
    }, b2 {
        @Override
        void fun() {
            System.out.println("b2");
        }
    };

    //枚举可以定义抽象方法(或者实现接口)
    abstract void fun();
}