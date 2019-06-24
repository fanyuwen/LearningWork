package com.learning.coreapi.lang;

/**
 * @author yuwen.fan
 * @date 2019/6/24 14:18
 * 核心类java.lang.StringBuilder简介(非线程安全)
 */
public class StrBuilder {

    /**
     * StringBuilder append方法的重载形式
     * {@link StringBuilder#append(char[], int, int)}
     * {@link StringBuilder#append(CharSequence, int, int)}
     */
    public void append() {
        StringBuilder sb = new StringBuilder();

        char[] chars = new char[]{'H', 'E', 'L', 'L', 'O'},
                chars1 = new char[]{'H', 'E', 'L', 'L', 'O', 'W', 'O', 'R', 'L', 'D'};

        //第一个参数字符数组
        //第二个参数是添加时该数组的起始偏移量(下标从0开始)
        //第三个参数是要添加的字符长度
        sb.append(chars, 0, chars.length);
        sb.append(chars1, 5, chars1.length - 5);
        System.out.println(sb.toString());//输出 HELLOWORLD
        //===============================================================================
        CharSequence charSequence = "XSSDI Love Programe";
        //第一个参数是CharSequence(String的父接口)
        //第二个参数是从charSequence起始下标(0开始)
        //第三个参数是该charSequence结束下标
        sb.append(charSequence, 4, charSequence.length());
        System.out.println(sb.toString());//输出 HELLOWORLDI Love Programe
    }

    /**
     * {@link java.lang.StringBuilder#setLength(int)}
     * {@link java.lang.StringBuilder#setCharAt(int, char)}
     */
    public void setLength() {
        StringBuilder sb = new StringBuilder();
        sb.append("1").append("2").append("3");
        System.out.println(sb.length());//3
        //设置当前的StringBuilder的长度,如果大于当前的StringBuilder长度则默认为0,小于StringBuilder长度则将下标改为设置的长度,但是实际的内容不变
        sb.setLength(4);
        System.out.println(sb.length());//4
        sb.setLength(2);
        System.out.println(sb.length());//2
    }
}
