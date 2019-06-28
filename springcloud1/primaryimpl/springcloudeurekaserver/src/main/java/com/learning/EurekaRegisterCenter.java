package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author fanyuwen
 * @date 2019/6/28 19:51
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisterCenter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterCenter.class, args);
    }
}