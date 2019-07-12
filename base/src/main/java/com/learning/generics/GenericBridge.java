package com.learning.generics;


import java.lang.reflect.Method;

/**
 * @author fanyuwen
 * @date 2019/7/12 14:08
 * 泛型桥方法讲解, 如果你看过或者正在看java核心技术上册  在泛型那一章是有介绍的
 */
public class GenericBridge implements Bridge<String> {


    /**
     * 熟悉反射都知道{@link Method}对象
     * 它有一个方法{@link Method#isBridge()}来判断该方法对象是否是一个桥方法
     * 下面这个方法是实现的泛型接口的具体实参(String)的实现
     * 但是因为泛型擦除{@link GenericsErasure}
     * 所以实际上父接口的方法声明是 Object get(); void set(Object param);
     * 但是如下所见为了实现泛型接口也就实现了以下方法
     * 本质上并没有进行实现,所以java编译器就自己实现了这两个方法,方法签名和上面是一样的
     * 但是为了要调用我们实现的这两个方法实现,所以就进行转发,如下代码:
     * {@code
     * public Object get() {
     * return get(); --> 调用返回String的get方法
     * }
     * public void set(Object param) {
     * set((String)param) --> 调用参数类型为String的set方法
     * }
     * }
     * 以上两个方法就被称为桥方法,注意: 生成的桥方法和实现的方法其实冲突的,因为方法名一样方法参数也一样,
     * 但是因为是编译器生成的,所以绕开了这个限制
     * 在方法重写时也会生成桥方法,当子类重写的方法返回值是父类方法返回值的子类型时
     *
     * @return String
     */
    @Override
    public String get() {
        return null;
    }

    @Override
    public void set(String param) {
        System.out.println(param);
    }


// 以下是编译器自动生成
//    public Object get() {
//        return get();
//    }

//    public void set(Object param) {
//        set((String) param);
//    }

    public static void main(String[] args) {
        Method[] methods = GenericBridge.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + (method.isBridge() ? "" : "不") + "是桥方法  ");
            System.out.println(method);
        }
    }
}

interface Bridge<T> {
    T get();

    void set(T param);
}
