package com.test.rocketmq.service.impl;


import com.test.rocketmq.dao.UserDao;
import com.test.rocketmq.service.IEmailConsumer;
import com.test.rocketmq.utils.EmailUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailConsumerImpl implements IEmailConsumer {


    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailUtil emailUtil;

    private DefaultMQPushConsumer consumer;


    public void startConsumer(String consumerGroup, String namesrvAddr, String topic, String tag) throws Exception{
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe(topic, tag);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt message : msgs) {
                    System.out.printf("Receive email message: %s%n", new String(message.getBody()));
                    // TODO: 实现邮件发送的逻辑
                    System.out.println("email send successfully:"+message);

                    emailUtil.sendMessage("13673505181@163.com","testSendEmailAsync",message.toString());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }



    public void shutdown() {
        consumer.shutdown();
    }

    public EmailConsumerImpl() {

    }
}
