package com.learning.designpattern.behavioralPattern.chainofResponsibility.handler;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.Request;

/**
 * @author fanyuwen
 * @date 2019/7/3 21:09
 */
public class BaiRenJieHandler extends AbstractRequestHandler{

    public BaiRenJieHandler(AbstractRequestHandler handler) {
        super(handler);
    }

    public BaiRenJieHandler(String name, AbstractRequestHandler handler){
        super(name, handler);
    }

    @Override
    public boolean canAnswer(Request request) {
        return true;
    }

    @Override
    public String result(Request request) {
        return "这个问题虽然难,但是我就是知道.";
    }
}