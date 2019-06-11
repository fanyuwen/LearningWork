package com.learning.dbcp.spring.dao;

import com.learning.dbcp.spring.bean.People;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleDao {

    /**
     * 返回新增的people主键
     * @param people
     * @return
     */
    int addPeople(People people);

    /**
     * 根据people bean进行查询
     * @param people
     * @return
     */
    List<People> selectListByPeople(People people);

}
