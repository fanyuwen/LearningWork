package com.learning.activeMqTest;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * activemq配置
 *
 * @author bairenjie
 * @Date 2019/06/25
 */
@Configuration
public class ActiveMqConfig {

    /**
     * 创建测试使用的队列
     * @author  bairenjie
     * @date    2019-06-25
     */
    @Bean
    public Queue queueTest(){
        return new ActiveMQQueue("Queue-1");
    }
    /**
     * 创建测试使用Topic
     * @author  bairenjie
     * @date    2019-06-25
     */
    @Bean
    public Topic topicTest(){
        return new ActiveMQTopic("Topic-1");
    }
}
