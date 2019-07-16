package com.learning.service;

import com.learning.annotation.MyComponent;
import com.learning.interfacepackage.Task;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author fanyuwen
 * @date 2019/7/4 18:03
 */
@MyComponent("bairenjie1234")
public class Bairenjie {

    @Resource
    private Map<String, Task> taskMap;


    public void show() {
        taskMap.forEach((k, v) -> {
            System.out.println("key is " + k);
            v.doTask();
        });
    }

}
