package com.learning.java8.lambda;


/**
 * Lambda 表达式可以简洁地理解为：可传递的匿名函数的一种方式：它没有名称，但是它有参数列表，函数主体，返回类型，可以抛出异常列表。
 *
 * @author pujing
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
        process(()-> System.out.println("hello world 3!"));
    }


    private static void process(Runnable r){
        r.run();
    }



}
