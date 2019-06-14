package com.learning.designpattern.factory.bairenjie;

import com.learning.designpattern.factory.OutstandingStudent;

/**
 * @author fanyuwen
 * @date 2019/6/14 15:09
 * 优秀学员 柏仁杰
 */
public class BaiRenJie implements OutstandingStudent {

    @Override
    public String name() {
        return "柏仁杰";
    }

    @Override
    public String achievement() {
        return "他学会了并且在工作当中实际使用了反射,研究了多数据源配置,自己利用空闲时间学习了zookeeper,还研究了SpringCloud,认真,对自己的认识很到位,未来一片光明";
    }

    @Override
    public String acceptanceSpeech() {
        return "非常感谢训练营的这次学习机会,虽然并没有完全领悟到所学的技术,但是凭借自己的努力还是达到了自己的目标,今后会一直努力的,谢谢大家!";
    }
}
