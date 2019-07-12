package com.learning.generics;

/**
 * @author fanyuwen
 * @date 2019/7/12 13:12
 * java泛型的实际运行 (泛型类型擦除)
 * java的泛型只会在编译期有效,实际运行的时候都会被擦除,不会在运行期保留具体的泛型信息
 */
public class GenericsErasure {
    /*
     * 申明消费的泛型类型为Integer类型,
     * 但是在实际运行的时候会被擦除为Object(泛型约束的上界, 如果 T extends String => 泛型T类型的上界是String,所以擦除之后的类型是String)
     */
    private ErasureB<Integer> integerErasureB;

    /**
     * 这里参数不设置泛型信息,忽略警告
     *
     * @param integerErasureB integerErasureB
     */
    @SuppressWarnings("unchecked")
    public void setIntegerErasureA(ErasureB integerErasureB) {
        this.integerErasureB = integerErasureB;
    }

    public void erasureResolve() {
        /*
         * 运行不会报错,虽然泛型类型是Integer,实际是String,但是会在运行期擦除,都是Object
         * erasureA的get方法返回的类型在运行期是Object,integerErasureB的set方法在运行期
         * 也是Object
         */
        ErasureA<Integer> erasureA = getErasureA();
        /**
         * 如下代码会在运行时报错,{@link ClassCastException} 字符串不能转成Integer
         * 因为编译器会在如下代码中添加一个类型转换(只有在赋值给一个变量的时候,传递给方法参数是不会转换的),这个是无感知的
         */
        Integer num = erasureA.get();
        /**
         * 如下代码不会添加类型转换
         */
        integerErasureB.set(erasureA.get());
    }

    @SuppressWarnings("unchecked")
    private <X> ErasureA<X> getErasureA() {
        return new ErasureA("123");
    }
}

class ErasureA<T> {

    private T t;

    public ErasureA(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }
}

class ErasureB<T> {

    void set(T param) {
        System.out.println(param);
    }

}