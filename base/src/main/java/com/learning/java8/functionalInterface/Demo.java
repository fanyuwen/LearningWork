package com.learning.java8.functionalInterface;


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
 * @date 2019/6/17 13:28
 */
public class Demo {

    /**
      函数式接口带有 @FunctionInterface 的标注。
      这个标注用于表示接口会设计成一个函数式接口。若使用了 @FunctionInterface 定义了一个接口，而它不是函数式接口，编译器将返回一个提示原因的错误。因此，对于设计函数式接口，@FunctionalInterface虽不是必需的，但使用它是比较好的做法。
     */

    public static void main(String[] args) {
        // 1、Predicate
        List<String> listOfStrings = Arrays.asList("a", "");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
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
     * 1、函数式接口 Predicate
     *
     * java.util.function.Predicate<T> 接口定义了一个名叫test的抽象方法，它接受泛型T对象，并返回一个boolean。 在你需要表示一个涉及类型T的布尔表达式时，可以使用这个接口。
     */
    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        // 法一：
//        List<T> results = new ArrayList<>();
//        for (T s : list) {
//            if (p.test(s)) {
//                results.add(s);
//            }
//        }
//        return results;

        // 法二：使用stream流
        return list.stream().filter(p).collect(Collectors.toList());
    }

    /**
     * 2、函数式接口 Consumer
     *
     * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型的对象，没有返回(void)。
     */
    private static <T> void forEach(List<T> list, Consumer<T> c) {
        // 法一：
//        for (T i : list) {
//            c.accept(i);
//        }

        // 法二：
        list.forEach(c);
    }

    /**
     * 3、函数式接口 Function
     *
     * java.util.function.Function<T, R>接口定义了一个叫作apply的方法，它接受一个泛型T的对象，并返回一个泛型R的对象。
     */
    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        // 1、法一：
//        List<R> result = new ArrayList<>();
//        for (T s : list) {
//            result.add(f.apply(s));
//        }
//        return result;

        // 2、法二：
        return list.stream().map(f).collect(Collectors.toList());
    }
}
