package com.learning.designpattern.factory.songwei;

import com.learning.designpattern.factory.OutstandingStudent;
import com.learning.designpattern.factory.TrainingCampFactory;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:41
 * 宋伟 工厂
 */
public class SongWeiFactory extends TrainingCampFactory {
    @Override
    public OutstandingStudent create() {
        return new SongWei();
    }
}
