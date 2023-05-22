package com.test.rocketmq.utils;//package com.test.rocketmq.utils;
//
//import com.test.rocketmq.configuration.EsConfig;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
///**
// * 使用configuration加bean注解的方式将bean注入到spring中
// *
// */
//@Slf4j
//@Configuration
//public class ElasticsearchUtil {
//
//    @Autowired
//    EsConfig esConfig;
//
//    @Bean(destroyMethod = "close", name = "client")
//    public RestHighLevelClient initRestClient() {
//        RestClientBuilder builder = RestClient.builder(new HttpHost(esConfig.getHost(), esConfig.getPort()))
//                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
//                        .setConnectTimeout(esConfig.getConnTimeout())
//                        .setSocketTimeout(esConfig.getSocketTimeout())
//                        .setConnectionRequestTimeout(esConfig.getConnectionRequestTimeout()));
//        return new RestHighLevelClient(builder);
//    }
//
//    // 注册 rest高级客户端
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost(esConfig.getHost(), esConfig.getPort(), "http")
//                )
//        );
//        return client;
//    }
//}
//
