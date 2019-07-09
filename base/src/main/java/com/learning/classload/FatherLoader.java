package com.learning.classload;

/**
 * @author fanyuwen
 * @date 2019/6/16 1:03
 */
public class FatherLoader implements GrandFatherLoader{
    static int initNUM(int num) {
        System.out.println(num);
        return num;
    }

    private static int f_static_age = initNUM(1);

    static {
        System.out.println(2);
    }

    private int f_dynamic_age = initNUM(3);

    {
        System.out.println(4);
    }

    void show(){
        System.out.println("13");
    }

    FatherLoader() {
        System.out.println(5);
        show();
    }
}
