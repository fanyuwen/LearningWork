package com.learning.feigninterface;

import com.learning.request.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fanyuwen
 * @date 2019/7/1 18:52
 */
@Component
@DependsOn("buildFeign")
public class SSSS {

    @Autowired
    private SpringFeignRequest request;

    @Autowired
    private SpringFeignRequest1 request1;

    public void sayHelloWorld(String body) {
        RestRequest<String> restRequest = new RestRequest<>();
        restRequest.setBody(body);
        System.out.println(request.sayHelloWorld(restRequest));
    }

    public void sayHelloWorld1(){
        System.out.println(request.sayHelloWorld());
    }

    public void showMessage(String hello) {
        Map<String, Object> map = new HashMap<>();
        map.put("hello", hello);
        System.out.println(request1.getMessage(map));
    }
}
