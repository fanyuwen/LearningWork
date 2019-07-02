package com.learning.feigninterface;

import com.learning.request.RestRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "testeurekaorigin",
        configuration = {
                FeignClientsConfiguration.class
        }
)
public interface SpringFeignRequest {

    @RequestMapping(value = "/helloworld",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8"
    )
    Message sayHelloWorld(RestRequest<String> restRequest);


    @RequestMapping(
            value = "/helloworld1",
            method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8"
    )
    Message sayHelloWorld();

}