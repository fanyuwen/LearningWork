package com.learning;

import com.learning.client.EurekaRestTemplateClient;
import com.learning.client.feign.FeignRequest;
import com.learning.client.feign.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fanyuwen
 * @date 2019/6/29 10:23
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients("com.learning.client")
@RestController
public class Application {

    @Autowired
    private EurekaRestTemplateClient client;

    @Autowired
    private FeignRequest request;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/show")
    public String show() {
        return request.sayHelloWorld();
    }

    @PostMapping("/message")
    public Message message(@RequestParam("hello") String hello) {
        return new Message(hello + "bairenjie");
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
