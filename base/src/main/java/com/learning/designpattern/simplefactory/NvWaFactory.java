package com.learning.designpattern.simplefactory;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 * 简单工厂模式(不属于GoF23种设计模式)，又名静态工厂模式，适用于固定的几种类型，基本不会添加新的类型的场景。
 * 当需要添加新的类型时，必须修改工厂类的逻辑。符合单一职责，不符合开闭原则。
 */
public class NvWaFactory {

    /**
     * 模拟女娲造人，根据入参判断需要创建的对象
     * @return Person
     */
    public static Person createPerson(String type) {
        Person person;
        switch (type) {
            case "M":
                person = new Man();
                break;
            case "W":
                person = new Woman();
                break;
            default:
                throw new RuntimeException("参数非法,你要造什么玩意儿？");
        }
        return person;
    }
}
