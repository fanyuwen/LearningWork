package com.learning.designpattern.structuralPattern.mvc;

/**
 * @author heshuzhuang
 * @version 1.0
 * Date: 2019/7/3
 */
public class Controller {
    private Person person;
    private PersonView view;



    public Controller(Person person, PersonView view) {
        this.person = person;
        this.view = view;
    }


    public String getName() {
        return person.getName();
    }

    public void setName(String name) {
        person.setName(name);
    }

    public String getPhone() {
        return person.getPhone();
    }

    public void setPhone(String phone) {
        person.setPhone(phone);
    }

    public String update(){
        return view.PersonMessage(person.getName(),person.getPhone());
    }
}
