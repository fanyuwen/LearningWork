package com.learning.generics;

/**
 * @author fanyuwen
 * 泛型类型为T
 */
public class Generic<T> {

    //通配符 PECS (Product(生产)(方法返回用 Extends)) (Consumer(消费)(方法参数用 Super))
    void pecs() {
        //因为是extends 所以只能调用生产方法
        B<? extends String> eb = new B<>();
        eb.get();
        //因为是super 所以只能调用消费方法
        B<? super String> sb = new B<>();
        sb.set("13123");
    }

    /**
     * java泛型与可变参数
     * 可变参数本质上是转换成数组,但是java不支持创建泛型数组(原因自己思考)
     * 当出现泛型类型的可变参数就会出现警告,无法创建参数化类型的可变参数
     * 因为会出现诸如 Generic<List<String>> => 就会转化成 List<String>[] (可以声明,但不能创建)
     * 这就会导致编译警告,处理方式是用{@link SuppressWarnings()}传入"unchecked",
     * jdk1.7版本以上新增了一个注解 {@link SafeVarargs} 表示安全的可变参数
    * @param param 参数化类型的可变参数
     */
    @SafeVarargs
//    @SuppressWarnings("unchecked")
    final void fun(T... param){

    }
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

    T get() {
        return value;
    }

    void set(T value) {
        this.value = value;
    }
}