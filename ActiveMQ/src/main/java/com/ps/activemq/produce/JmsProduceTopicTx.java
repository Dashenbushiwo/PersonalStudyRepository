package com.ps.activemq.produce;

import com.ps.activemq.constant.Constant;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 事务偏生产者，签收偏消费者
 */
public class JmsProduceTopicTx {

    public static void main(String[] args) throws JMSException {
        // 1. 创建连接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(Constant.ACTIVE_MQ_URL);
        // 2. 通过连接工厂，获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();

        // 3. 创建会话session
        // 两个参数，第一个叫事务/第二个叫签收
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（具体是队列还是主题）
        Topic topic = session.createTopic(Constant.TOPIC_NAME_03);
        // 5. 创建消息的生产者；注意：持久化需要把connection.start()放在设置持久化下面执行
        MessageProducer messageProducer = session.createProducer(topic);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();
        // 6. 通过使用messageProducer生产3条消息发送到MQ的队列里面
        for (int i = 0; i < 3; i++) {
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("Topic_msg_persist---" + i);
            // 8. 通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }
        // 9. 开启了事务，必须commit才会批量提交消息，类似jdbc事务
        session.commit();
        // 10. 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("*****TOPIC_消息发布到MQ完成");
    }

}
