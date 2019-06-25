package com.learning.activeMqTest;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * 消费者---用来监听消息生产者并对消息做出处理
 *
 * @author bairenjie
 * @Date 2019/06/25
 */
@Service
public class Consumer {

    // 用来监听Queue-1队列

    @JmsListener(destination = "Queue-1")
    public void consumTest(String message){
        System.out.println("收到Queue-1的消息" + message);
    }

    @JmsListener(destination = "Topic-1")
    public void consumTopic(String message){
        System.out.println("订阅Topic-1的消息---1" + message);
    }

    @JmsListener(destination = "Topic-1")
    public void consumTopic1(String message){
        System.out.println("订阅Topic-1的消息---2" + message);
    }
}
