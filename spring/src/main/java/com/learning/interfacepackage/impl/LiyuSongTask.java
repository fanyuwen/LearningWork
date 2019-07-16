package com.learning.interfacepackage.impl;

import com.learning.interfacepackage.Task;
import org.springframework.stereotype.Component;

/**
 * @author fanyuwen
 * @date 2019/7/16 14:53
 */
@Component
public class LiyuSongTask implements Task {

    @Override
    public void doTask() {
        System.out.println("I'm linyusong");
    }
}
