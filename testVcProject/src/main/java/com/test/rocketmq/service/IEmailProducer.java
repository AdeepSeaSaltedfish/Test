package com.test.rocketmq.service;

import org.apache.rocketmq.client.exception.MQClientException;

public interface IEmailProducer {

    public void startEmailProducer(String producerGroup, String namesrvAddr) throws MQClientException;

    public void sendEmail(String topic, String emailContent) throws Exception;
}
