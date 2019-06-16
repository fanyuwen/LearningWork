package com.sw.cloud.user.repository;

import com.sw.cloud.user.table.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SONGWEI
 * @version 1.0
 * Date: 2019/6/16/016
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
