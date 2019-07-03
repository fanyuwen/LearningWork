package com.learning.designpattern.creationalPattern.factory;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:07
 * 训练营优秀学员接口
 */
public interface OutstandingStudent {

    /**
     * 优秀学员的名字
     * @return 名字表示
     */
    String name();

    /**
     * 获得的成就
     * @return 成就字符串描述
     */
    String achievement();

    /**
     * 获奖感言
     * @return 发自肺腑的感言
     */
    String acceptanceSpeech();

}