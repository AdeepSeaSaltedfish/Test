package com.test.rocketmq.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc  // 开启springmvc
@EnableTransactionManagement   // 开启事务
@MapperScan("com.test.rocketmq.dao")
public class AppConfig {

}
