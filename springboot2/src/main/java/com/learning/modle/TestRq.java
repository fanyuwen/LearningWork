package com.learning.modle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 测试入参类
 * @author bairenjie
 * @date 2019/9/30 14:10
 */

@ApiModel(description = "测试入参类")
public class TestRq {

    @ApiModelProperty(value = "姓名",name = "name",required = true)
    private String name;

    @ApiModelProperty(value = "年龄",name = "age",required = true)
    private Integer age;

    @ApiModelProperty(value = "地址",name = "address",required = true)
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "TestRq{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
