package com.learning.designpattern.factory;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:04
 * 训练营工厂生产优秀学员
 */
public abstract class TrainingCampFactory {

    /**
     * 生产优秀学员的方法
     *
     * @return 实现优秀学员接口的对象
     */
    public abstract OutstandingStudent create();

}
