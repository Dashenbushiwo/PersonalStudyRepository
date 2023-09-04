package com.ps.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.time.LocalDateTime;

@Component
public class TopicConsumer {

    @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("topic 消费者收到消息-" + LocalDateTime.now());
        System.out.println("myTopic 收到消息内容：" + textMessage.getText());
    }
}
