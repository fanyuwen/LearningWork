package com.learning.dbcp.spring.bean;

import java.io.Serializable;
import java.util.Date;

public class People implements Serializable {
    //主键id
    private int id;
    //姓名
    private String name;
    //年龄
    private int age;
    //兴趣爱好
    private String hobby;
    //创建时间
    private Date esTime;
    //更新时间
    private Date upTime;

    public People(String name, int age, String hobby, Date esTime, Date upTime) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
        this.esTime = esTime;
        this.upTime = upTime;
    }

    public People(String name, int age, String hobby) {
        this(name, age, hobby, new Date(), new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Date getEsTime() {
        return esTime;
    }

    public void setEsTime(Date esTime) {
        this.esTime = esTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}