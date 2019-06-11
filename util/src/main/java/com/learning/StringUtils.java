package com.learning;

import java.util.regex.Pattern;

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

    private static final Pattern HEX_PATTERN = Pattern.compile("^[0-9a-fA-F]+$");

    /**
     * 判断参数字符串是否是十六进制数字表示法
     * @param str 参数字符串
     * @return true:是十六进制表示法,otherwise:false
     */
    public static boolean isHexStr(String str) {
        return HEX_PATTERN.matcher(str).matches();
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
     *
     * @param str 参数字符串
     * @return true:非空,false:空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}