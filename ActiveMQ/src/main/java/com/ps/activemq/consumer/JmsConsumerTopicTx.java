package com.ps.activemq.consumer;

import com.ps.activemq.constant.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消费者偏签收
 */
public class JmsConsumerTopicTx {

    public static void main(String[] args) throws JMSException, IOException {
        // 1. 创建连接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(Constant.ACTIVE_MQ_URL);
        // 2. 通过连接工厂，获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        // 持久化必填
        connection.setClientID("XZX01");
        // 3. 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收；
        // 开启了事务则优先按事务处理，commit后就自动签收
        // 如果没开启事务且如果开启了Session.CLIENT_ACKNOWLEDGE手动签收，
        // 则收到消息要Message调用acknowledge方法
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // 4. 创建目的地（具体是队列还是主题）
        Topic topic = session.createTopic(Constant.TOPIC_NAME_03);
        // 5. 持久化需要创建订阅者后再执行connection.start()
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark...");
        connection.start();
        while (true) {
            // 6. 消费者接收信息，参数即等待时间，不传参数会一直等待；传参，则等待时间过后将会返回null
            Message receive = durableSubscriber.receive(3000);
            if (receive != null) {
                System.out.println("消费者收到消息：" + ((TextMessage)receive).getText());
                receive.acknowledge();
            } else {
                System.out.println("没消息了");
                break;
            }
        }
        // 7. 开启了事务程序结尾必须commit，不然会引起重复消费；既再次调用程序时，对应消息还会被接收到
//        session.commit();
        // 8. 关闭资源
        durableSubscriber.close();
        session.close();
        connection.close();
    }

}
