package com.learning.util;

/**
 * @author fanyuwen
 */
public class StringUtils {
    /**
     * 私有构造函数,防止创建该类对象
     * 构造函数抛出异常,防止通过反射创建该对象
     */
    private StringUtils() {
        throw new RuntimeException("can't create the class " + StringUtils.class + " object.");
    }

    /**
     * 判断该字符串是否为空(null,"")
     *
     * @param str 参数字符串
     * @return true:空,false:不为空
     */
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (!Character.isWhitespace(ch))
                return false;
        }
        return true;
    }

    /**
     * 判断该字符串是否为非空()
     * @param str 参数字符串
     * @return true:非空,false:空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}