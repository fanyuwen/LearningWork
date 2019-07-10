package com.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fanyuwen
 * @date 2019/7/10 9:21
 */
@Configuration
public class AnotherConfig {
    @Bean
    public Set<String> aaaa(@Autowired @Qualifier("aaaa") List<String> list) {
        return new HashSet<>(list);
    }

}
