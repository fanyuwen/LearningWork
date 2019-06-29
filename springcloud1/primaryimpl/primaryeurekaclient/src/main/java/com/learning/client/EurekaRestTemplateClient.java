package com.learning.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author fanyuwen
 * @date 2019/6/29 10:55
 */
@Component
public class EurekaRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    public String showHelloWorld() {
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://testeurekaorigin/helloworld", HttpMethod.GET, null, String.class);
        return responseEntity.getBody();
    }

}