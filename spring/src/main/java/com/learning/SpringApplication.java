package com.learning;

import com.learning.service.Bairenjie;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author fanyuwen
 * Spring总的启动类(采用java bean创建的方式)
 */
public class SpringApplication {
    private static final ConfigurableApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfigure.class);

    public static void main(String[] args) {
        Bairenjie bairenjie = APPLICATION_CONTEXT.getBean(Bairenjie.class);
        bairenjie.show();
    }
}
