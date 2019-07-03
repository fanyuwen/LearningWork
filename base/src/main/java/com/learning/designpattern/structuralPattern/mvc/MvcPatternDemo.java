package com.learning.designpattern.structuralPattern.mvc;

/**
 * mvc  设计模式思想 model -- view  -- controller
 * @author heshuzhuang
 * @version 1.0
 * Date: 2019/7/3
 */
public class MvcPatternDemo {
    public static void main(String[] args) {
        // 初始化人员信息
        Person he = new Person();
        he.setName("柏仁杰");
        he.setPhone("188888888");

        // 创建视图
        PersonView view = new PersonView();
        Controller heController = new Controller(he,view);
        String baiMessage =  heController.update();
        System.out.println(baiMessage);

        heController.setName("何书壮");
        String heMessage = heController.update();
        System.out.println(heMessage);
    }
}
