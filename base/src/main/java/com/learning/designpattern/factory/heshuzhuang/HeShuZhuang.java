package com.learning.designpattern.factory.heshuzhuang;

import com.learning.designpattern.factory.OutstandingStudent;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:19
 * 优秀学员 何书壮
 */
public class HeShuZhuang implements OutstandingStudent {
    @Override
    public String name() {
        return "何书壮";
    }

    @Override
    public String achievement() {
        return "他是训练营优秀的谦逊者,无论生活还是工作都一丝不苟,在工作中也是勤勤恳恳,对新技术抱有浓厚的兴趣,也会经常反思,不停的进步,总能在对的时间做出正确的决定,未来会是一个出色的领导者";
    }

    @Override
    public String acceptanceSpeech() {
        return "感谢训练营的培养,虽然时间不长,但是能够跟这么多优秀的待在一起,也是我的荣幸,我只是侥幸获得这个殊荣而已,非常感谢一直帮助我的其它同学,后面我会再努力的,谢谢大家!";
    }
}
