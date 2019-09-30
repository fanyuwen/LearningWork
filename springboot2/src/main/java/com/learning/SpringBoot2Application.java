package com.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author fanyuwen
 * @date 2019/6/16 0:53
 */
@EnableSwagger2
@SpringBootApplication
public class SpringBoot2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2Application.class, args);
    }
}
