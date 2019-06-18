package com.sw.cloud.user.controller;

import com.sw.cloud.user.repository.UserRepo;
import com.sw.cloud.user.table.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SONGWEI
 * @date 2019/6/18 11:15
 */
@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepo.findOne(id);
    }

}
