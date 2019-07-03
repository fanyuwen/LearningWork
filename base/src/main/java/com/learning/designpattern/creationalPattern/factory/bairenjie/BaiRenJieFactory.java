package com.learning.designpattern.creationalPattern.factory.bairenjie;

import com.learning.designpattern.creationalPattern.factory.OutstandingStudent;
import com.learning.designpattern.creationalPattern.factory.TrainingCampFactory;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:17
 * 柏仁杰 工厂
 */
public class BaiRenJieFactory extends TrainingCampFactory {
    @Override
    public OutstandingStudent create() {
        return new BaiRenJie();
    }
}
