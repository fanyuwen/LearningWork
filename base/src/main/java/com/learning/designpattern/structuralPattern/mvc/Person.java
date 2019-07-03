package com.learning.designpattern.structuralPattern.mvc;


import java.io.Serializable;

/**
 * @author heshuzhuang
 * @version 1.0
 * Date: 2019/7/3
 */
public class Person implements Serializable {

    private String name;

    private String phone;

    public String getName() {
  return name;
 }

    public void setName(String name) {
  this.name = name;
 }

    public String getPhone() {
  return phone;
 }

    public void setPhone(String phone) {
  this.phone = phone;
 }

   @Override
   public String toString() { return "Person{" +
          "name='" + name + '\'' +
          ", phone='" + phone + '\'' +
          '}';
   }
}
