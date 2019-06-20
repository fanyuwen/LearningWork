package com.learning.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author yuwen.fan
 * @date 2019/6/19 18:13
 */
@Configuration
@ComponentScan(value = "com.learning.shiro", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
})
public class RootConfig {
}
