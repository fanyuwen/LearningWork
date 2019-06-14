package com.learning.datatype;

/**
 * @author fanyuwen
 */
public class FDouble {
    //double 类型数据长度为64 最高位符号位(+-)
    private double value;

    public static void main(String[] args) {
        new FDouble().expression();
    }

    void compare(double value1, double value2) {
        //使用
        int result;
        if ((result = Double.compare(value1, value2)) == 0) {
            System.out.println("相等");
        } else if (result > 0) {
            System.out.println("value1 大于 value2");
        } else {
            System.out.println("value2 大于 value1");
        }
    }

    void expression() {
        //浮点数 科学技术法 12.23 * 10 ^ 2
        double d = 12.23e2;
        //十六进制(jdk1.5) 0x1是十六进制 1 * 2 ^ 2 详情见 java核心技术卷1
        double p = 0x1p2;
        System.out.println("科学计数法以10为底: " + d);
        System.out.println("十六进制以2为底: " + p);
    }
}
