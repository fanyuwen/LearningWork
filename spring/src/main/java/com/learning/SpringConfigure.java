package com.learning;

import com.learning.resource.ResourceTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.net.MalformedURLException;
import java.util.Arrays;

/**
 * @author Spring的总配置类
 */
@Configuration
public class SpringConfigure {

    //基于java bean的方式将指定路径上的文件加载为 org.springframework.core.io.Resource
    //Resource指代一个路径资源,任何类型的资源,Spring都可以将它加载成为Resource
    public ResourceTest createResource() throws MalformedURLException {
        ResourceTest resourceTest = new ResourceTest();
        Resource[] resources = new Resource[0];
        String path = "";
        //基于类加载路径的
        ClassPathResource classPathResource = new ClassPathResource(path);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = classPathResource;
        //基于系统文件路径的
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = fileSystemResource;
        //基于url文件路径的
        FileUrlResource fileUrlResource = new FileUrlResource(path);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = fileUrlResource;
        //基于文件流
        InputStreamResource inputStreamResource = new InputStreamResource(null);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = inputStreamResource;
        //字节数组
        ByteArrayResource byteArrayResource = new ByteArrayResource(new byte[0]);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = byteArrayResource;
        //描述的(没有实际用处)
        DescriptiveResource descriptiveResource = new DescriptiveResource(path);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = descriptiveResource;
        //重点介绍PathMatchingResourcePatternResolver:可以通过正则进行模式匹配
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = pathMatchingResourcePatternResolver.getResource(path);
        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = resource;

        resourceTest.setResource(classPathResource);
        return resourceTest;
    }
}