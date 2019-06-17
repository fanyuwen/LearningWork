package com.learning.java8.lambda;


import java.util.function.Consumer;

/**
 * Lambda 表达式可以简洁地理解为：可传递的匿名函数的一种方式：它没有名称，但是它有参数列表，函数主体，返回类型，可以抛出异常列表。
 *
 * @author pujing
 * @author fanyuwen
 * @date 2019/6/14 15:31
 */
public class Lambda {

    /*
      Lambda 的基本语法：
       (parameters) -> expression  或   (parameters) -> { statements; }

       parameters：参数列表。
       -> ： 箭头。把参数列表把Lambda主体分隔开来。
       expression：Lambda的返回值。
       statements：Lambda主体。

      eg:
       () -> void : 参数列表为空，且返回void函数。
     */


    /*
      Lambda 的使用场景： 函数式接口。
      注：函数式接口：只定义一个抽象方法的接口。eg: Predicate<T>，Comparator<T>， Runnable

      Lambda 表达式允许直接以内联的形式作为函数式接口的抽象方法提供实现，并把整个表达式作为函数式接口的实例。
      （具体来说，是函数式接口一个具体实现的实例）
     */
    public static void main(String[] args) {
        // 1、lambda 表达式
        Runnable r1 = () -> System.out.println("hello world 1!");
        process(r1);

        // 2、匿名类
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world 2!");
            }
        };
        process(r2);

        // 3、直接传递lambda表达式
        process(() -> System.out.println("hello world 3!"));
    }

    //成员属性
    private String name;

    /**
     * lambda作用域问题
     *
     * @author fanyuwen
     */
    void scope() {
        int age = 0;

        /*
         * 在成员(非静态)方法中定义的lambda表达式可以访问到所在类的成员属性
         * 注意:静态方法时无法访问的
         */
        Consumer<String> con_member_property = t -> System.out.println(t + name);

        /*
         * 在任何方法中,lambda表达式都能访问已声明并初始化的局部变量,但是该局部变量不能再赋值,等价于被final修饰
         */
        Consumer<String> con_local_variable = t -> System.out.println(t + age);

        /*
         * 要注意在成员(非静态)方法中定义的lambda表达式的this问题
         * lambda中的this就是指代的所在类的当前对象
         */
        Consumer<String> con_this = t -> {
            this.show();
            process(() -> System.out.println("Lambda this expression"));
        };

    }

    /**
     * 成员方法
     */
    private void show() {
        System.out.println("Lambda this expression");
    }

    private static void process(Runnable r) {
        r.run();
    }


}
