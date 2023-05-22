package com.test.rocketmq.service.impl;

import com.test.rocketmq.service.IEmailProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailProducerImpl implements IEmailProducer {

    private DefaultMQProducer producer;

    public void startEmailProducer(String producerGroup, String namesrvAddr) throws MQClientException {
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setDefaultTopicQueueNums(1);
        producer.start();
    }

    public void sendEmail(String topic, String emailContent) throws Exception {
        Message message = new Message(topic, emailContent.getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(message, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.printf("Send email success, result=%s%n", sendResult);
            }

            public void onException(Throwable e) {
                System.out.printf("Send email failed, error=%s%n", e.getMessage());
            }
        });
    }

    public void shutdown() {
        producer.shutdown();
    }

    public EmailProducerImpl() {
    }
}
