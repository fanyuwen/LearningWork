package com.learning.designpattern.factory.bairenjie;

import com.learning.designpattern.factory.OutstandingStudent;
import com.learning.designpattern.factory.TrainingCampFactory;

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
