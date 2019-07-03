package com.learning.designpattern.behavioralPattern.chainofResponsibility;

import com.learning.designpattern.behavioralPattern.chainofResponsibility.handler.AbstractRequestHandler;

/**
 * @author fanyuwen
 * @date 2019/7/3 21:03
 */
public class Request {
    private String question;

    public Request(String question) {
        this.question = question;
    }

    public void resolve(AbstractRequestHandler handler) {
        System.out.println("问题: " + question);
        if (handler.canAnswer(this)) {
            System.out.println(handler.getName() + "能够回答");
            System.out.println("他的答案是: " + handler.result(this));
        } else {
            System.out.println(handler.getName() + "不能回答");
            System.out.println("因为他说: " + handler.result(this));
        }
    }

    public String getQuestion() {
        return question;
    }
}