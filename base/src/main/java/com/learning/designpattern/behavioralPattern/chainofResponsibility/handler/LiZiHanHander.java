package com.learning.designpattern.behavioralPattern.chainofResponsibility.handler;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.Request;

/**
 * @author fanyuwen
 * @date 2019/7/3 21:13
 */
public class LiZiHanHander extends AbstractRequestHandler {

    public LiZiHanHander() {
    }

    public LiZiHanHander(String name, AbstractRequestHandler handler) {
        super(name, handler);
    }

    public LiZiHanHander(String name) {
        this(name, null);
    }

    @Override
    public boolean canAnswer(Request request) {
        return false;
    }

    @Override
    public String result(Request request) {
        return "不要问我,这个我真不会";
    }
}
