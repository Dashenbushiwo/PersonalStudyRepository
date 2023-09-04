package com.ps.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.time.LocalDateTime;

@Component
public class QueueConsumer {

    @JmsListener(destination = "${myQueue}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者收到消息-" + LocalDateTime.now());
        System.out.println("收到消息内容：" + textMessage.getText());
    }
}
