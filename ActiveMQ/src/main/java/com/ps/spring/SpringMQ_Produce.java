package com.ps.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {

    private final JmsTemplate jmsTemplate;

    public SpringMQ_Produce(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        SpringMQ_Produce produce = (SpringMQ_Produce)ctx.getBean("springMQ_Produce");
        produce.jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage("******spring和activeMQ的整合case");
            return textMessage;
        });
    }

}
