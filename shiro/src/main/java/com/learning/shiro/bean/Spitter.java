package com.learning.shiro.bean;

/**
 * @author fanyuwen
 * @date 2019/6/21 8:23
 */
public class Spitter {
    private final Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Spitter(String firstName, String lastName, String username, String password) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}