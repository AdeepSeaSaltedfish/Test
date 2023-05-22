package com.test.rocketmq.service;

public interface IEmailConsumer {
    public void startConsumer(String consumerGroup, String namesrvAddr, String topic, String tag) throws Exception;
}
