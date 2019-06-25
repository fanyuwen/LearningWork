package com.learning.syntax;

/**
 * @author fanyuwen
 * @date 2019/6/25 15:42
 */
public class JavaSyntax {

    /**
     * 显示一个复杂的方法调用表达式的先后顺序
     * 挑战你对java语法执行顺序的了解
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
