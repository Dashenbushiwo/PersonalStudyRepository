package com.ps.activemq.consumer;

import com.ps.activemq.constant.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumerTopic {

    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(Constant.ACTIVE_MQ_URL);
        // 2. 通过连接工厂，获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3. 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（具体是队列还是主题）
        Topic topic = session.createTopic(Constant.TOPIC_NAME_01);
        // 5. 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic);
        //region 接收消息：同步阻塞方式
        // 订阅者或接收者调用receive方法来接收消息，receive方法在能够接收前（或超时前）将一直阻塞
        /*while (true) {
            // 6. 消费者接收信息，参数即等待时间，不传参数会一直等待；传参，则等待时间过后将会返回null
//            Message receive = messageConsumer.receive(3000);
            Message receive = messageConsumer.receive();
            if (receive != null) {
                System.out.println("消费者收到消息：" + ((TextMessage)receive).getText());
            } else {
                System.out.println("没消息了");
                break;
            }
        }*/
        //endregion
        //region 接收消息：异步非阻塞方法，通过监听的方式来接收消息
        messageConsumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Topic消费者（监听模式）收到消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 监听方式中卡主主线程，否则主线程会直接结束
        System.in.read();
        //endregion
        // 7. 关闭资源
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
