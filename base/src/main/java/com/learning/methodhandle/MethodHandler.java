package com.learning.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

/**
 * @author fanyuwen
 * @date 2019/7/7 22:53
 * jdk1.7新增的方法句柄api
 */
public class MethodHandler {

    /**
     * 方法句柄查找对象(用于查找指定对象的方法句柄)
     */
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static void main(String[] args) {
        new MethodHandler().testGetterAndSetter();
    }

    private void testGetterAndSetter() {
        try {
            //创建obj对象的age 属性的getter methodhandle对象
            MethodHandle methodHandleGetter = lookup.findGetter(Obj.class, "age", int.class);
            //创建obj对象的age 属性的setter methodhandle对象
            MethodHandle methodHandleSetter = lookup.findSetter(Obj.class, "age", int.class);
            Obj obj = new Obj();
            //将obj对象的属性设置为12
            methodHandleSetter.invoke(obj, 12);
            //获取obj对象的属性
            Object object = methodHandleGetter.invoke(obj);
            //进行打印
            System.out.println("result is " + object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //这是捕获findGetter方法抛出的异常
            e.printStackTrace();
        } catch (Throwable e){
            //这是用来捕获methodHandle的invoke方法调用
        }
    }

}