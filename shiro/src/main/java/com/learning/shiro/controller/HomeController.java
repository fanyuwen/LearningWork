package com.learning.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fanyuwen
 * @date 2019/6/20 10:31
 */
@Controller
@RequestMapping({"/", "/homepage"})
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(){
        Subject subject = SecurityUtils.getSubject();
        return "home";
    }

}