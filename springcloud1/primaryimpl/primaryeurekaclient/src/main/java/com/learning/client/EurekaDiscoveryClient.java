package com.learning.client;

import com.learning.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author fanyuwen
 * @date 2019/6/29 10:28
 */
@Component
public class EurekaDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String showHelloWorld() {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("testeurekaorigin");

        if (CollectionUtils.isEmpty(serviceInstances)) {
            return "";
        }
        String serverUrl = String.format("%s/helloworld", serviceInstances.get(0).getUri().toString());
        ResponseEntity<String> responseEntity = restTemplate.exchange(serverUrl, HttpMethod.GET, null, String.class);

        return responseEntity.getBody();
    }
}
