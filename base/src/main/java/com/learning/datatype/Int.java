package com.learning.datatype;

/**
 * @author fanyuwen
 */
public class Int {
    //int 类型数据长度为32位 最高位符号位(+-)  2147483647(0x7fffffff) ~ -2147483648(0x80000000)
    private int num;

    public static void main(String[] args) {
        System.out.println("Hello World");
        definedInt();
    }

    /**
     * 算术运算溢出问题
     */
    void overflow() {
        int num = 0x7fff_ffff;//最大值
        num = num + 1;
        System.out.println(num);//变成最小值了(0x80000000)
    }

    /**
     * 自动装箱缓存问题
     * {@link java.lang.Integer@IntegerCache}
     */
    void cache() {
        //目前通过源码可知在装箱操作中 -128 ~ 127 是有缓存的
        Integer a = 126;//等同于调用 Integer.valueOf(126)
        Integer b = 126;
        System.out.println(a == b);//true

        a = 129;
        b = 129;
        System.out.println(a == b);//false
    }

    /**
     * 从java7开始，可以给数字下面加下划线，让人更加易读。
     * java编译器会剔除这些下划线。
     */
    static void definedInt() {
        int a = 100_000_000;
        System.out.println(a);
    }
}