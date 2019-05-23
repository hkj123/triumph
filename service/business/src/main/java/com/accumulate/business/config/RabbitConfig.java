//package com.accumulate.business.config;
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//    //redirect模式
//    final public static String QUEUE_NAME = "queue.name";
//    final public static String TEST_TOPIC_ROUTINGKEY = "test.topic.routingKey";
//    final public static String EXCHANGE = "test.exchange";
//    /**
//     * direct模式
//     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
//     * @return
//     */
//    @Bean
//    public Queue directQueue1() {
//        return new Queue(QUEUE_NAME);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding directBinding1() {
//        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(TEST_TOPIC_ROUTINGKEY);
//    }
//}