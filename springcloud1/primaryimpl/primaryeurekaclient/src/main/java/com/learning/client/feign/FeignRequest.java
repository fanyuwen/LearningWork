package com.learning.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author fanyuwen
 * @date 2019/6/29 13:20
 */
@FeignClient("testeurekaorigin")
public interface FeignRequest {

    @RequestMapping(method = RequestMethod.GET, value = "/helloworld")
    String sayHelloWorld();
}
