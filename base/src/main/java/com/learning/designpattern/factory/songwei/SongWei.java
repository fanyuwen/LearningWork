package com.learning.designpattern.factory.songwei;

import com.learning.designpattern.factory.OutstandingStudent;

/**
 * @author fanyuwen
 * @date  2019/6/14 15:35
 * 优秀学员 宋伟
 */
public class SongWei implements OutstandingStudent {

    @Override
    public String name() {
        return "宋伟";
    }

    @Override
    public String achievement() {
        return "他热切的好奇心,强烈的钻研精神,是训练营不可多得的实干家,对技术的热爱还能影响周边的同学,每当有成就或者收货总是想分享给大,这种乐于分享的精神是难能可贵的,将来必将是一位响当当的架构师";
    }

    @Override
    public String acceptanceSpeech() {
        return "其实也没啥好说,训练营的氛围很好,给我提供了很好的学习环境,怎么说呢?心怀感谢,我愿意帮助大家,如果大家有什么问题,尽管找我,谢谢!";
    }
}
