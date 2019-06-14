package com.learning.designpattern.factory.heshuzhuang;

import com.learning.designpattern.factory.OutstandingStudent;
import com.learning.designpattern.factory.TrainingCampFactory;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:32
 * 何书壮 工厂
 */
public class HeShuZhuangFactory extends TrainingCampFactory {

    @Override
    public OutstandingStudent create() {
        return new HeShuZhuang();
    }
}
