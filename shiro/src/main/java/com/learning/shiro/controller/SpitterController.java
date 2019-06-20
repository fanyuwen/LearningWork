package com.learning.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fanyuwen
 * @date 2019/6/21 0:14
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "registerForm";
    }

}
