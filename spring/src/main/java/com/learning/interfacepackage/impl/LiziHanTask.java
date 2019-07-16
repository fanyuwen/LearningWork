package com.learning.interfacepackage.impl;

import com.learning.interfacepackage.Task;
import org.springframework.stereotype.Component;

/**
 * @author fanyuwen
 * @date 2019/7/16 14:52
 */
@Component
public class LiziHanTask implements Task {
    @Override
    public void doTask() {
        System.out.println("I'm lizihan");
    }
}
