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

    private static final int[] HEX_BYTE_CHAR;

    static {
        HEX_BYTE_CHAR = new int['f' + 1];
        for (int i = 0; i < 10; i++) {
            HEX_BYTE_CHAR[i] = (char) ('0' + i);
        }
        for (int i = 10; i < 16; i++) {
            HEX_BYTE_CHAR[i] = (char) ('a' + i - 10);
        }
        for (char first = '0'; first < '9' + 1; first++) {
            HEX_BYTE_CHAR[first] = first - '0';
        }
        for (char first = 'a'; first < 'f' + 1; first++) {
            HEX_BYTE_CHAR[first] = first - 'a' + 10;
        }
        for (char first = 'A'; first < 'F' + 1; first++) {
            HEX_BYTE_CHAR[first] = first - 'A' + 10;
        }
    }

    /**
     * 将字节(用int是因为基本类型的自动转换)转换成字符
     *
     * @param num 指定的字节值
     * @return 对应的字符表示
     */
    private static char byteToChar(int num) {
        if ((num & ~0xf) != 0) {
            throw new IllegalArgumentException("num is beyond the range.must be 0 ~ 15");
        }
        return (char) HEX_BYTE_CHAR[num];
    }

    /**
     * 将字符转换成字节表示
     *
     * @param ch 需要转换的字符
     * @return 对应字符的字节表示
     */
    private static byte charToByte(char ch) {
        if (!HEX_PATTERN.matcher(String.valueOf(ch)).matches()) {
            throw new IllegalArgumentException("ch is not the hex char.");
        }
        return (byte) HEX_BYTE_CHAR[ch];
    }

    /**
     * 将十六进制字符串表示转换为对应的字节数组
     *
     * @return 转换之后的字数组
     */
    public static byte[] convertHexStrToBytes(String str) {
        if (!HEX_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException("参数必须为十六进制字符串表示");
        }
        int len;
        if (((len = str.length()) & 1) == 1) {
            throw new IllegalArgumentException("参数的字符串长度必须为偶数个");
        }
        //创建新的字节数组
        byte[] bytes = new byte[len >>> 1];
        char[] chars = str.toCharArray();
        for (int i = 0; i < bytes.length; i++) {
            char first = chars[2 * i];
            char second = chars[2 * i + 1];
            bytes[i] = (byte) (charToByte(first) << 4 | charToByte(second));
        }
        return bytes;
    }

    /**
     * 将字节数组转换为对应的十六进制数字符串表示
     *
     * @param bytes
     * @return
     */
    public static String convertBytesToHexStr(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte num : bytes) {
            int low = num & 0xf;
            int high = num >> 4 & 0xf;
            sb.append(byteToChar(high)).append(byteToChar(low));
        }
        return sb.toString();
    }

    /**
     * 判断参数字符串是否是十六进制数字表示法
     *
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