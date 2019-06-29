package com.learning;

import java.util.Collection;

/**
 * @author fanyuwen
 * @date 2019/6/29 10:32
 */
public class CollectionUtils {
    private CollectionUtils() {
        throw new RuntimeException("can't create the class " + CollectionUtils.class + " object.");
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 判断集合
     * @return 为空:true,否则:false
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 判断集合
     * @return 不为空:true,否则:false
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}