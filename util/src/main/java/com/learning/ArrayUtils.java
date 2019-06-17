package com.learning;

import java.util.function.Predicate;

/**
 * @author fanyuwen
 * @date 2019/6/16 16:05
 * 数组工具类
 */
public class ArrayUtils {
    private ArrayUtils() {
        throw new RuntimeException("can't create the class " + ArrayUtils.class + " object.");
    }

    /**
     * 判断对象数组中的元素是否都是为空(字符串类型则是""或者null)
     *
     * @param array 参数数组
     * @param <T>   泛型T
     * @return true:数组元素都为空,otherwise:false
     */
    public static <T> boolean isAllEmpty(T[] array) {
        return judgeArray(array, EmptyLogic.ALL);
    }

    /**
     * 判断对象数组中的元素是否有任意一个或者多个元素为空
     *
     * @param array 参数数组
     * @param <T>   泛型T
     * @return true:数组有元素为空,otherwise:false
     */
    public static <T> boolean isAnyEmpty(T[] array) {
        return judgeArray(array, EmptyLogic.ANY);
    }

    /**
     * 判断对象数组中的元素是否都不为空
     *
     * @param array 参数数组
     * @param <T>   泛型T
     * @return true:数组元素都不为空,otherwise:false
     */
    public static <T> boolean isNoneEmpty(T[] array) {
        return judgeArray(array, EmptyLogic.NONE);
    }

    private static <T> boolean judgeArray(T[] array, EmptyLogic emptyLogic) {
        if (array == null || array.length == 0) {
            return emptyLogic.empty();
        }
        for (T ele : array) {
            if (emptyLogic.check(ele instanceof String ?
                    StringUtils.isNotEmpty((String) ele) : ele != null)) {
                return emptyLogic.result();
            }
        }
        return !emptyLogic.result();
    }

    /**
     * 参考jdk8 Stream
     * {@link java.util.stream.Stream#allMatch(Predicate)}
     * {@link java.util.stream.Stream#anyMatch(Predicate)}
     * {@link java.util.stream.Stream#noneMatch(Predicate)}
     * {@see java.util.stream.ReferencePipeline implements java.util.stream.Stream}
     * {@see 参考java.util.stream.MatchOps.MatchKind枚举实现, 感兴趣的同时可以去观摩大师写的jdk的源码}
     */
    private enum EmptyLogic {
        ALL(true, false, true), ANY(false, true, true), NONE(false, false, false);
        private boolean condition;
        private boolean result;
        private boolean isEmpty;

        EmptyLogic(boolean noEmptyCondition, boolean result, boolean isEmpty) {
            //约定noEmptyCondition参数代表 对象非空为true,空(字符串 "",null)为false
            this.condition = noEmptyCondition;
            this.result = result;
            this.isEmpty = isEmpty;
        }

        boolean check(boolean expression) {
            return expression == condition;
        }

        boolean result() {
            return result;
        }

        boolean empty() {
            return isEmpty;
        }
    }

}