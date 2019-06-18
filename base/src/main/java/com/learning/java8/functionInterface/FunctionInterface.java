package com.learning.java8.functionInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 函数式接口：定义且只定义了一个抽象方法（可以有多种默认方法）。
 *
 * @author pujing
 * @author fanyuwen
 * @date 2019/6/17 13:28
 */
public class FunctionInterface {

    /**
     * 就是这么不要脸, 抄袭的java.util.function.Function实现
     * {@code @FunctionalInterface标注的接口只能有一个抽象的方法实现,但是可以定义从 java.lang.Object类定义的抽象的方法 }
     * see java.util.function.* jdk已经提供了非常多的函数式接口,灵活运用它们吧
     *
     * @param <T> 入参类型
     * @param <R> 返回类型
     * @author fanyuwen
     */
    @FunctionalInterface
    interface MyFunction<T, R> extends Function<T, R> {
        R apply(T param);

        /**
         * 这是java.lang.Object类中定义的实现方法,默认是已经被实现的,
         * 不属于函数式接口只能有一个抽象方法的实现要求,所以满足条件
         * Object的其它方法同样可以在接口里定义成抽象的
         *
         * @return string
         */
        String toString();
    }

    /**
     * 还是不要脸, 抄袭的java.util.function.Consumer实现
     *
     * @param <T> 方法入参类型
     * @author fanyuwen
     */
    @FunctionalInterface
    interface MyConsumer<T> extends Consumer<T> {
        void accept(T param);

        /**
         * 接口可以使用默认的方法(public),不属于抽象方法实现标准,所以经过FunctionalInterface注解校验检查
         *
         * @param param 方法入参
         */
        default void defaultAccept(T param) {
            System.out.println(param);
        }
    }

    /**
     * 脸要了干啥, 不要问了就是抄袭的java.util.function.Predicate实现
     * @author fanyuwen
     * @param <T> 入参类型
     */
    @FunctionalInterface
    interface MyPredicate<T> extends Predicate<T> {
        boolean test(T param);

        /**
         * 接口可以声明静态方法(public),不属于抽象方法实现标准,所以经过FunctionalInterface注解校验检查
         *
         * @param param 方法入参
         * @param <T>   入参泛型类型
         * @return boolean 对象是否为空
         */
        static <T> boolean simpleTest(T param) {
            return param != null;
        }
    }

    /**
     * 函数式接口带有 @FunctionInterface 的标注。
     * 这个标注用于表示接口会设计成一个函数式接口。若使用了 @FunctionInterface 定义了一个接口，而它不是函数式接口，编译器将返回一个提示原因的错误。因此，对于设计函数式接口，@FunctionalInterface虽不是必需的，但使用它是比较好的做法。
     * @author fanyuwen修改
     */
    public static void main(String[] args) {
        // 1、Predicate
        List<String> listOfStrings = Arrays.asList("a", "");
        MyPredicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println("过滤list中空字符串后得到：" + nonEmpty.toString());

        // 2、Consumer
        // 此处 System.out::println 等同于 (Integer i) -> System.out.println(i)
        forEach(Arrays.asList(1, 2, 3, 4, 5), System.out::println);

        // 3、Function
        // 此处 String::length 等同于 (String s) -> s.length()
        List<Integer> l = map(Arrays.asList("abc", "a", "ab"), String::length);
        System.out.println(l.toString());
    }

    /**
     * 1、函数式接口 MyPredicate
     * <p>
     * java.util.function.Predicate<T> 接口定义了一个名叫test的抽象方法，它接受泛型T对象，并返回一个boolean。 在你需要表示一个涉及类型T的布尔表达式时，可以使用这个接口。
     * @author fanyuwen修改
     */
    private static <T> List<T> filter(List<T> list, MyPredicate<T> p) {
        // 法一：
        List<T> results = new ArrayList<>();
        for (T s : list) {
            if (p.test(s)) {
                results.add(s);
            }
        }

        // 法二：使用stream流
        results = list.stream().filter(p).collect(Collectors.toList());
        return results;
    }

    /**
     * 2、函数式接口 MyConsumer
     * <p>
     * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型的对象，没有返回(void)。
     * @author fanyuwen修改
     */
    private static <T> void forEach(List<T> list, MyConsumer<T> c) {
        // 法一：
        for (T i : list) {
            c.accept(i);
        }

        // 法二：
        list.forEach(c);
    }

    /**
     * 3、函数式接口 MyFunction
     * <p>
     * java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。
     * @author fanyuwen修改
     */
    private static <T, R> List<R> map(List<T> list, MyFunction<T, R> f) {
        // 1、法一：
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }

        // 2、法二：
        result = list.stream().map(f).collect(Collectors.toList());
        return result;
    }
}