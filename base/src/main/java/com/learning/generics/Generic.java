package com.learning.generics;

/**
 * @author fanyuwen
 * 泛型类型为T
 */
public class Generic<T> {

}

/**
 * 泛型约束  T 必须是继承Comparable的子类型,然而Comparable的泛型类型可以是T的父类型(泛型逆变)
 *
 * @param <T>
 */
class A<T extends Comparable<? super T>> {
}

/**
 * 泛型通配符
 *
 * @param <T>
 */
class B<T> {
    private T value;

    /**
     * 上界通配符:只能调用返回值方法,可以用约束的上界类型来引用返回值
     *
     * @param param param
     */
    void upperBound(B<? extends T> param) {
        T t = param.get();
        System.out.println(t);
    }

    /**
     * 下界通配符:只能调用消费方法(即泛型约束类型为方法参数),
     * 可以用约束的下界类型或其子类型的值作为方法参数
     *
     * @param param    param
     * @param newValue newvalue
     */
    <X extends T> void lowerBound(B<? super T> param, X newValue) {
        param.set(newValue);
    }

    private T get() {
        return value;
    }

    private void set(T value) {
        this.value = value;
    }
}