package com.learning.feigninterface;

import feign.Headers;
import feign.RequestLine;

@Headers({ "Content-Type: application/json", "Accept: application/json" })
public interface FeignRequest {

    @RequestLine("GET /helloworld")
    Message sayHelloWorld();

}
