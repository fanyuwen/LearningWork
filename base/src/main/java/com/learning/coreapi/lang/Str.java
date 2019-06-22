package com.learning.coreapi.lang;

/**
 * @author fanyuwen
 * @date 2019/6/22 15:40
 * 核心类 java.lang.String简介
 */
public class Str {

    public void memory() {
        //编译器检查 "+” 连接的表达式,如果是也是字面量(final常量,包括基本类型字面量),则能够在编译期确认,自动连接,结果放入常量池
        String s1 = "1" + "2" + "3";
        /*
         * 当使用 "+” 连接字符串中含有变量时,也是在运行期才能确定的。
         * 首先连接操作最开始时如果都是字符串常量,编译后将尽可能多的字符串常量(final常量,基本类型字面量)连接在一起，
         * 形成新的字符串常量参与后续的连接
         *
         * 接下来的字符串连接是从左向右依次进行,对于不同的字符串,首先以最左边的字符串为参数创建StringBuilder对象(可变字符串对象),
         * 然后依次对右边进行append操作,最后将StringBuilder对象通过toString()方法转换成String对象
         * (注意:中间的多个字符串常量不会自动拼接) 经过测试发现后面出现的连续的字符串常量(不包括基本类型常量)拼接会自动拼接并且存放到静态常量区
         *
         * 当使用 + 进行多个字符串连接时,实际上是产生了一个StringBuilder对象和一个String对象
         */
        String s2 = "1" + "3" + new String("1") + "4" + "5";
        s2 = "1" + 3 + new String("1") + "4" + "5";
        //等价于
        s2 = new StringBuilder("13").append(new String("1")).append("45").toString();
        //===========================================================================
        s2 = "1" + "3" + new String("1") + 4 + "5";
        //等价于
        s2 = new StringBuilder("13").append(new String("1")).append(4).append("5").toString();
    }

    /**
     * JVM字符串常量池简介
     * JAVA虚拟机(JVM)中存在着一个字符串池，其中保存着很多String对象
     * 并且可以被共享使用，因此它提高了效率
     * 由于String类是final的，它的值一经创建就不可改变
     * 字符串池由String类维护，我们可以调用intern()方法来访问字符串池
     */
    public void constantPool() {
        String willinternStr = new String(new char[]{'1', '2', '3'});
        //将字符串123 添加到常量池里
        willinternStr.intern();

        //在字符串池创建了一个对象
        String s1 = "abc";
        //字符串pool已经存在对象“abc”(共享),所以创建0个对象，累计创建一个对象
        String s2 = "abc";
        //true 指向同一个对象
        System.out.println("s1 == s2 : " + (s1 == s2));
        //true  值相等
        System.out.println("s1.equals(s2) : " + (s1.equals(s2)));

        //=========================================================================

        //创建了两个对象，一个存放在字符串池中，一个存在于堆区中
        //还有一个对象引用s3存放在栈中
        String s3 = new String("abc");
        //字符串池中已经存在"abc"对象，所以只在堆中创建了一个对象
        String s4 = new String("abc");
        //false s3和s4栈区的地址不同，指向堆区的不同地址
        System.out.println("s3 == s4 : " + (s3 == s4));
        //true s3和s4的值相同
        System.out.println("s3.equals(s4) : " + (s3.equals(s4)));
        //false 存放的地区多不同，一个静态常量区，一个堆区
        System.out.println("s1 == s3 : " + (s1 == s3));
        //true 值相同
        System.out.println("s1.equals(s3) : " + (s1.equals(s3)));

        //==========================================================================

        //由于常量的值在编译的时候就被确定(优化)了。
        //在这里，"ab"和"cd"都是常量，因此变量str3的值在编译时就可以确定。
        //这行代码编译后的效果等同于： String str3 = "abcd";
        String str1 = "ab" + "cd";  //1个对象
        String str11 = "abcd";
        System.out.println("str1 = str11 : " + (str1 == str11));

        //==========================================================================

        /*
         * 局部变量str2,str3存储的是存储两个拘留字符串对象(intern字符串对象)的地址。
         * 第三行代码原理(str2+str3)：
         * 运行期JVM首先会在堆中创建一个StringBuilder类，
         * 同时用str2指向的拘留字符串对象完成初始化，
         * 然后调用append方法完成对str3所指向的拘留字符串的合并，
         * 接着调用StringBuilder的toString()方法在堆中创建一个String对象，
         * 最后将刚生成的String对象的堆地址存放在局部变量str4中。
         *
         * 而str5存储的是字符串池中"abcd"所对应的拘留字符串对象的地址。
         * str4与str5地址当然不一样了。
         *
         * 内存中实际上有五个字符串对象：三个拘留字符串对象、一个String对象和一个StringBuilder对象。
         */

        String str2 = "ab";  //1个对象
        String str3 = "cd";  //1个对象
        String str4 = str2 + str3;
        String str5 = "abcd";
        System.out.println("str4 = str5 : " + (str4 == str5)); // false

        //==========================================================================

        /*
         * JAVA编译器对string + 基本类型/常量 是当成常量表达式直接求值来优化的。
         * 运行期的两个string相加，会产生新的对象的，存储在堆(heap)中
         */

        String str6 = "b";
        String str7 = "a" + str6;
        String str67 = "ab";//
        //str6为变量，在运行期才会被解析。
        System.out.println("str7 = str67 : " + (str7 == str67));
        final String str8 = "b";
        String str9 = "a" + str8;
        String str89 = "ab";
        //str8为常量变量，编译期会被优化
        System.out.println("str9 = str89 : " + (str9 == str89));
    }


    /**
     * 字符串 String 的hashCode会进行缓存,hash缓存{@link String#hashCode()}
     *
     * @return String的hashcode
     */
    public int hash(String s) {
        //为了解决hash冲突,所以必须要找一个素数,保证散列的均衡分散
        int result = 17;// (1 << 4) + 1
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            result = result * 31 + ch;// 1 << 5 - 1
        }
        return result;
    }

    public static void main(String[] args) {
        String d = "ss";
        int n = 3;
        String rr = "1" + n + d + "4" + "5";
        String s3 = new String("4") + new String("5");
        System.out.println(s3 == s3.intern());

        s3 = new String("1") + new String("3ss45");
        System.out.println(s3 == s3.intern());
    }

}
