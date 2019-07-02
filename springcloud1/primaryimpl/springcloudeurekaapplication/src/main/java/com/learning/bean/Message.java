package com.learning.bean;

/**
 * @author fanyuwen
 * @date 2019/7/1 13:12
 */
public final class Message {
    private String name;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}