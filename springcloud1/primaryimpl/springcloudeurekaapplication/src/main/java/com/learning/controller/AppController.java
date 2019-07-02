package com.learning.controller;

import com.learning.bean.Message;
import com.learning.bean.request.RestRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanyuwen
 * @date 2019/6/29 8:15
 */
@RestController
@RequestMapping("/")
public class AppController {

    @PostMapping("helloworld")
    public Message sayHelloWorld(RestRequest<String> restRequest) {
        return new Message("Hello World" + restRequest.getBody());
    }

    @GetMapping("helloworld1")
    public Message sayHelloWorld() {
        return new Message("hello world+++++");
    }

}