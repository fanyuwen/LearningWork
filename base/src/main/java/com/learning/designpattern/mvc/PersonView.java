package com.learning.designpattern.mvc;


/**
 * @author heshuzhuang
 * @version 1.0
 * Date: 2019/7/3
 */
public class PersonView {

    public String  PersonMessage(String name,String phone) {
        return "hello ！ 我叫" + name + "，我的手机号码是：" + phone;
    }
}
