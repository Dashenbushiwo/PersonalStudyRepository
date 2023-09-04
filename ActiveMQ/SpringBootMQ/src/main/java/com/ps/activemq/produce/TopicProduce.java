package com.ps.activemq.produce;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.time.LocalDateTime;

@Component
public class TopicProduce {

    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Topic topic;

    public TopicProduce(JmsMessagingTemplate jmsMessagingTemplate, Topic topic) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.topic = topic;
    }

    // 间隔时间三秒钟定投
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(topic, "主题生产者生产消息scheduled: " + LocalDateTime.now());
        System.out.println("topic scheduled time:" + LocalDateTime.now());
    }
}
