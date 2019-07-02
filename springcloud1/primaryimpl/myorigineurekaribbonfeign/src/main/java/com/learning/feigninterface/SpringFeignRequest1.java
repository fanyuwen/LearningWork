package com.learning.feigninterface;

import feign.QueryMap;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author fanyuwen
 * @date 2019/7/2 1:36
 */
@FeignClient(name = "testeurekaorigininvoker", configuration = FeignClientsConfiguration.class)
public interface SpringFeignRequest1 {

    @RequestMapping(value = "/message",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8"
    )
    Message getMessage(@QueryMap Map<String, Object> map);

}