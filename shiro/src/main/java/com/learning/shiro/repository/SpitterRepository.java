package com.learning.shiro.repository;

import com.learning.shiro.bean.Spitter;
import org.springframework.stereotype.Repository;

/**
 * @author fanyuwen
 * @date 2019/6/21 9:54
 */
@Repository
public interface SpitterRepository {

    Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}