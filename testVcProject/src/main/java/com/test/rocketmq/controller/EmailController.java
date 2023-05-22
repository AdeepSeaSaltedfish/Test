package com.test.rocketmq.controller;


import com.test.rocketmq.service.IEmailConsumer;
import com.test.rocketmq.service.IEmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private IEmailProducer emailProducer;

    @Autowired
    private IEmailConsumer emailConsumer;


    @GetMapping("/producer")
    public void firstEmailProducer(){
        try {
            emailProducer.startEmailProducer("my-producer-group","127.0.0.1:9876");
            emailProducer.sendEmail("firstEmail","13673505181@163.com");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/consumer")
    public void firstEmailConsumer(){
        try {
            emailConsumer.startConsumer("my-producer-group","127.0.0.1:9876","firstEmail","");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


