//package com.accumulate.business.config;
//
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//
//import javax.jms.ConnectionFactory;
//import javax.jms.Queue;
//
///**
// * Activitymq配置类
// */
//@Configuration
//public class JmsConfig {
//    public final static String QUEUE = "springboot.queue.test";
//
//    @Bean
//    public Queue queue() {
//        return new ActiveMQQueue(QUEUE);
//    }
//    // queue模式的ListenerContainer
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
//        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//        bean.setConnectionFactory(activeMQConnectionFactory);
//        return bean;
//    }
//
//}
