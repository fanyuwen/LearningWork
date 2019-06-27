package com.learning.syntax;

/**
 * @author fanyuwen
 * @date 2019/6/25 15:42
 */
public class JavaSyntax {

    /**
     * 方法执行的完整流程
     * 涉及java语言语法分析的过程
     */
    static void showJavaSyntaxOrderOfMethodInvoke() {
        origin().first(generateFirstNum())
                .second(generateSecondNum())
                .third(generateThirdNum());
    }

    static JavaSyntax origin() {
        System.out.println("origin");
        return new JavaSyntax();
    }

    JavaSyntax first(int num) {
        System.out.println("first" + num);
        return this;
    }

    static int generateFirstNum() {
        System.out.println("firstNum");
        return 1;
    }

    JavaSyntax second(int num) {
        System.out.println("second" + num);
        return this;
    }

    static int generateSecondNum() {
        System.out.println("secondNum");
        return 2;
    }

    void third(int num) {
        System.out.println("third" + num);
    }

    static int generateThirdNum() {
        System.out.println("thirdNum");
        return 3;
    }
}
