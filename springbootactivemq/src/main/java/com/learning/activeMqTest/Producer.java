package com.learning.activeMqTest;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * activemq消息生产者
 *
 * @author bairenjie
 * @Date 2019/06/25
 */
@RestController
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/test/activeQueue")
    public String queueTest(@RequestBody String message){
        jmsTemplate.convertAndSend("Queue-1",message);
        return "queue producer is ok";
    }

    @RequestMapping("/test/activeTopic")
    public String topicTest(@RequestBody String message){
        Test test = new Test();
        test.setAge(12);
        test.setName("xiaoming");
        test.setAddress("suzhou");
        test.setSex(0);
        jmsTemplate.convertAndSend("Topic-1", JSONObject.toJSONString(test));
        return "topic producer is ok";
    }
}
