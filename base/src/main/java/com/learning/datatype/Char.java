package com.learning.datatype;

/**
 * @author fanyuwen
 */
public class Char {
    //char 字符类型, 16位 没有符号位 0 ~ 65535(0xffff)
    private char ch;

    /**
     * java语言本身就是支持Unicode字符编码的,所以编写的java代码里面的每一个字符都支持unicode 详情见 java核心技术卷1
     * <p>
     * 下面的unicode描述等价于fun方法的定义, 你甚至可以直接将该unicode代码粘贴到外面  效果是一样的
     * \u0070\u0075\u0062\u006c\u0069\u0063 \u0076\u006f\u0069\u0064 \u0066\u0075\u006e\u0028\u0029 \u007b
     * \u0053\u0079\u0073\u0074\u0065\u006d\u002e\u006f\u0075\u0074\u002e\u0070\u0072\u0069\u006e\u0074\u006c\u006e\u0028\u0022\u006a\u0061\u0076\u0061\u4f7f\u7528\u0075\u006e\u0069\u0063\u006f\u0064\u0065\u7f16\u7801\u0022\u0029\u003b
     * \u007d
     */
    public void fun() {
        System.out.println("java使用unicode编码");
    }


}