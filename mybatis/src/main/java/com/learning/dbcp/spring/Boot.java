package com.learning.dbcp.spring;

import com.learning.dbcp.spring.bean.People;
import com.learning.dbcp.spring.dao.PeopleDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Boot {
    private static final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);

    public static void main(String[] args) {
        PeopleDao peopleDao = context.getBean(PeopleDao.class);
        int result = 0;
        for (People people : friends) {
            result += peopleDao.addPeople(people);
        }
        System.out.println("新增" + result + "条数据");
        context.close();
    }

    private static People[] friends;

    static {
        friends = new People[2];
        friends[0] = new People("张雨", 25, "嘴炮 研究汽车");
        friends[1] = new People("夏寅", 24, "真实 研究");
    }
}