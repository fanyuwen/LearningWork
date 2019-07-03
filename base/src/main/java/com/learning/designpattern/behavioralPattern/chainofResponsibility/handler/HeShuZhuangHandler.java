package com.learning.designpattern.behavioralPattern.chainofResponsibility.handler;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.Request;

/**
 * @author fanyuwen
 * @date 2019/7/3 21:11
 */
public class HeShuZhuangHandler extends AbstractRequestHandler {

    public HeShuZhuangHandler(AbstractRequestHandler handler) {
        super(handler);
    }

    public HeShuZhuangHandler(String name, AbstractRequestHandler handler) {
        super(name, handler);
    }

    @Override
    public boolean canAnswer(Request request) {
        return true;
    }

    @Override
    public String result(Request request) {
        return request.getQuestion() + "这个问题啊?应该要这么回答。。。。";
    }
}
