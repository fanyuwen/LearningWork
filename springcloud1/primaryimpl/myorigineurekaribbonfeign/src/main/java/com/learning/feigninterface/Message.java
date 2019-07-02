package com.learning.feigninterface;

/**
 * @author fanyuwen
 * @date 2019/7/1 13:14
 */
public final class Message {
    private String name;

    public Message() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message [name is " + name + "]";
    }
}
