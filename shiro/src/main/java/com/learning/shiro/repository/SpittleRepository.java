package com.learning.shiro.repository;

import com.learning.shiro.bean.Spittle;
import java.util.List;

/**
 * @author fanyuwen
 * @date 2019/6/20 11:41
 */
public interface SpittleRepository {
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);
}
