package com.learning.activeMqTest;

import java.io.Serializable;

/**
 * 类详情
 *
 * @author bairenjie
 * @Date 2019/06/26
 */
public class Test implements Serializable{

    private static final long serialVersionUID = 6267925573913172346L;
    private Integer age;

    private String name;

    private String address;

    private Integer sex;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Test{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                '}';
    }
}
