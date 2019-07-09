package com.learning.classload;

/**
 * @author fanyuwen
 * 父接口的常量属性初始化是不会被子接口或者子类触发的,只有在真正调用的时候才会初始化
 */
public interface GrandFatherLoader {

    int num = getNum();

    static int getNum(){
        System.out.println("init the grandfather's properties");
        return 1;
    }
}