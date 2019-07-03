package com.learning.designpattern.creationalPattern.simplefactory;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/12/012
 */
public class Client {

    public static void main(String[] args) {
        Person person;
        // 调用简单工厂创建对象
        person = NvWaFactory.createPerson("M");
        person.eat();


        System.out.println("----------------------");
        person = NvWaFactory.createPerson("W");
        person.eat();
    }
}
