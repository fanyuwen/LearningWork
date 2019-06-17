package com.learning.java8.methodReference;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：根据已有的方法实现来创建的lambda表达式，可以被看作仅仅调用特定方法的lambda的一种快捷写法。
 *
 * @author pujing
 * @date 2019/6/17 15:15
 */
public class MethodReference {

    /**
     * 如何构建方法引用：
     *
     * 1、指向静态方法的方法引用。
     *      eg：Integer 的 parseInt方法，写作：Integer::parseInt。 lambda表达式：(String s) -> Integer.parseInt(s)。
     *
     * 2、指向任意类型实例方法的方法引用。
     *      eg：String 的 length 方法，写作：String::length。 lambda表达式：(String s) -> s.length()。
     *
     * 3、指定现有对象的实例方法的方法引用。
     *      eg：假设你有一个局部变量expensiveTransaction用于存放Transaction类型的对象，它支持实例方法getValue。写作：expensiveTransaction::getValue。
     */

    public static void main(String[] args) {
        /*
         * 构造函数引用
         * 对于一个现有构造函数，你可以利用它的名称和关键字 new 来创建它的一个引用：ClassName::new。它的功能与指向静态方法的引用类似。
         */


        /*
           <1> 假设一个构造函数没有参数，它适合Supplier的签名()->T。
           ①构造函数引用指向默认的 Apple() 构造函数，等价于：() -> new Apple()；②调用Supplier的get方法产生一个新的Apple。
         */
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();

        /*
           <2> 假设一个构造函数有一个参数，它适合Function的签名()->T。
           ①构造函数引用指向Apple(Integer weight)构造函数，等价于：() -> new Apple(weight)；②调用Function的apply方法，并给出重量，将产生一个新的Apple。
         */
        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(100);
    }

}

class Apple{
    private Integer weight;

    public Apple() {
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }
}
