package com.learning.designpattern.behavioralPattern.chainofResponsibility.handler;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.Request;

/**
 * @author fanyuwen
 * @date 2019/7/3 20:59
 * 责任链模式使用
 */
public abstract class AbstractRequestHandler {
    private String name;
    private AbstractRequestHandler nextHandler;

    public AbstractRequestHandler() {
    }

    public AbstractRequestHandler(AbstractRequestHandler handler) {
        this.nextHandler = handler;
    }

    public AbstractRequestHandler(String name, AbstractRequestHandler handler) {
        this.name = name;
        this.nextHandler = handler;
    }



    public final void resolve(Request request) {
        request.resolve(this);
        AbstractRequestHandler nextHandler = getNextHandler();
        if (nextHandler != null) {
            nextHandler.resolve(request);
        }
    }

    public abstract boolean canAnswer(Request request);

    public abstract String result(Request request);

    public String getName() {
        return name;
    }

    public AbstractRequestHandler getNextHandler() {
        return nextHandler;
    }
}