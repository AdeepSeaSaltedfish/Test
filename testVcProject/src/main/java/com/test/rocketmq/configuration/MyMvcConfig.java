package com.test.rocketmq.configuration;//package com.test.rocketmq.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//@EnableWebMvc
//public class MyMvcConfig implements WebMvcConfigurer {
//
//    /**
//     *  拦截某个请求跳转到固定位置
//     *
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.jsp");
//    }
//
//    /**
//     * 静态资源处理
//     */
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/static/**")
////                .addResourceLocations("classpath:/webapp/static/");
////    }
//
//    /**
//     * 默认servlet请求
//     *
//     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable(); // 开启默认的servlet处理器
//    }
//}
