package com.ps.activemq.produce;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.time.LocalDateTime;

@Component
public class QueueProduce {

    private final JmsMessagingTemplate jmsMessagingTemplate;
    private final Queue queue;

    public QueueProduce(JmsMessagingTemplate jmsMessagingTemplate, Queue queue) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
        this.queue = queue;
    }

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "生产者生产消息: " + LocalDateTime.now());
    }

    // 间隔时间三秒钟定投
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "生产者生产消息scheduled: " + LocalDateTime.now());
        System.out.println("scheduled time:" + LocalDateTime.now());
    }
}
