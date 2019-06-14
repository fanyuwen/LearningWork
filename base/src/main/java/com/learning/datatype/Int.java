package com.learning.datatype;

/**
 * @author fanyuwen
 */
public class Int {
    /**
     * int 类型数据长度为32位 最高位符号位(+-)  2147483647(0x7fffffff) ~ -2147483648(0x80000000)
     */
    private int num;

    public static void main(String[] args) {
        System.out.println("Hello World");
        definedInt();
    }

    /**
     * 算术运算溢出问题
     */
    void overflow() {
        //最大值
        int num = 0x7fff_ffff;
        num = num + 1;
        //变成最小值了(0x80000000)
        System.out.println(num);
    }

    /**
     * 自动装箱缓存问题
     * {@link java.lang.Integer@IntegerCache}
     */
    void cache() {
        //目前通过源码可知在装箱操作中 -128 ~ 127 是有缓存的
        //等同于调用 Integer.valueOf(126)
        Integer a = 126;
        Integer b = 126;
        //true (但是工作中不要这样比较, Integer.compare() 或者是 equals)
        System.out.println(a == b);

        a = 129;
        b = 129;
        //false
        System.out.println(a == b);
    }

    /**
     * @author SONGWEI
     * 从java7开始，可以给数字字面量下面加下划线，让人更加易读。
     * java编译器会剔除这些下划线。
     */
    static void definedInt() {
        int a = 100_000_000;
        System.out.println(a);
    }

    void expression() {
        //十六进制表示
        int hex = 0x12;
        //八进制表示(容易与十进制混淆)
        int octal = 017;
        //二进制表示(jdk新增)
        int binary = 0b01110101;
        System.out.println("hex: " + hex);
        System.out.println("octal: " + octal);
        System.out.println("binary: " + binary);
    }
}