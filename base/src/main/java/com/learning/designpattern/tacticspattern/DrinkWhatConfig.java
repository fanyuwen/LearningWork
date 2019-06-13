package com.learning.designpattern.tacticspattern;

/**
 *
 * 决定你具体喝什么
 * User: heshuzhuang
 * Date: 2019/6/13
 * Time: 15:32
 */

public class DrinkWhatConfig {

    private RoleHsz roleHsz;

    public DrinkWhatConfig(RoleHsz roleHsz){
        this.roleHsz = roleHsz;
    }
    public String drinkWhat(){
        return roleHsz.drink();
    }


    public static void main(String[] args) {

        // 喝红酒
        DrinkWhatConfig whatConfig = new DrinkWhatConfig(new RedWine());
        System.out.println(whatConfig.drinkWhat());

        // 喝水
        DrinkWhatConfig waterConfig = new DrinkWhatConfig(new Water());
        System.out.println(waterConfig.drinkWhat());

    }
}