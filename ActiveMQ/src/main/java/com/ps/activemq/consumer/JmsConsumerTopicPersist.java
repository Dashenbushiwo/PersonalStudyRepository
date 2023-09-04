package com.ps.activemq.consumer;

import com.ps.activemq.constant.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 持久化topic
 * 1.一定要先运行一次消费者，等于向MQ注册，类似我订阅了这个主题
 * 2.然后再运行生产者发送消息，此时
 * 3.无论消费者是否在线，都会接收到，不在线的话，下次连接的时候，会把没有收过的消息都接收下来
 */
public class JmsConsumerTopicPersist {

    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(Constant.ACTIVE_MQ_URL);
        // 2. 通过连接工厂，获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        // 持久化必填
        connection.setClientID("XZX01");
        // 3. 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（具体是队列还是主题）
        Topic topic = session.createTopic(Constant.TOPIC_NAME_02);
        // 持久化需要创建订阅者后再执行connection.start()
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        durableSubscriber.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Topic_persist消费者（监听模式）收到消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        // 5. 关闭资源
        durableSubscriber.close();
        session.close();
        connection.close();
    }

}
