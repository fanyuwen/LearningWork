package com.learning.designpattern.creationalPattern.factory.songwei;

import com.learning.designpattern.creationalPattern.factory.OutstandingStudent;
import com.learning.designpattern.creationalPattern.factory.TrainingCampFactory;

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
