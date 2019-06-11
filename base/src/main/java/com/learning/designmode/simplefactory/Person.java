package com.learning.designmode.simplefactory;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 */
public interface Person {
    /** 接口定义行为规范，具体实现交给子类 */
    void eat();
}

class Man implements Person {

    public Man() {
        System.out.println("男人被捏出来了");
    }
    @Override
    public void eat() {
        System.out.println("狼吞虎咽");
    }
}
class Woman implements Person {

    public Woman() {
        System.out.println("女人被捏出来了");
    }
    @Override
    public void eat() {
        System.out.println("细嚼慢咽");
    }
}