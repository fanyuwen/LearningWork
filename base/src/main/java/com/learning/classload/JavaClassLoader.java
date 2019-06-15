package com.learning.classload;

/**
 * @author fanyuwen
 * @date 2019/6/15 22:36
 * 测试java对象创建初始化顺序
 */
public class JavaClassLoader extends FatherLoader {
    static {
        System.out.println("6");
    }

    private static int static_age = initNUM(7);

    {
        System.out.println("8");
    }

    private int dynamic_age = initNUM(9);

    private JavaClassLoader() {
        System.out.println("10");
        show();
    }

    void show(){
        System.out.println("12");
    }

    public static void main(String[] args) {
        System.out.println(11);
        new JavaClassLoader();
        //柏 1 2 6 7 11 3 4 5 8 9 10 12 12
        //樊 1 2 6 7 11 3 4 5 12 8 9 10 12
    }
}