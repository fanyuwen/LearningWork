package com.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanyuwen
 * @date 2019/6/29 8:15
 */
@RestController
@RequestMapping("/")
public class AppController {

    @GetMapping("helloworld")
    public String sayHelloWorld() {
        return "Hello World";
    }

}