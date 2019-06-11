package com.learning.resource;

import org.springframework.core.io.Resource;

public class ResourceTest {
    //需要一个资源
    private Resource resource;
    //需要多个资源
    private Resource[] resources;

    public void show() {
        System.out.println("resource: " + resource);
        if (resources != null) {
            for (Resource resource : resources) {
                System.out.println("resource: " + resource);
            }
        }
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setResources(Resource[] resources) {
        this.resources = resources;
    }
}