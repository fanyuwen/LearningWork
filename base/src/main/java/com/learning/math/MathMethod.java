package com.learning.math;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 */
public class MathMethod {

    public static void main(String[] args) {

        countSqrt();

        countPow();
    }


    /**
     * 计算一个数的平方根
     */
    static void countSqrt() {
        double x = 9;
        double y = Math.sqrt(x);
        System.out.println(y);
    }

    /**
     * 幂运算
     * y = （4 的 3 次方）
     */
    static void countPow() {
        double x = 4;
        double y = Math.pow(x, 3);
        System.out.println(y);
    }
}
