package com.sw.cloud.user.repository;

import com.sw.cloud.user.table.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SONGWEI
 * @date 2019/6/18 10:49
 */
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
