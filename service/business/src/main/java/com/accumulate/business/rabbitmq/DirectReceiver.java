package com.accumulate.business.rabbitmq;

import com.accumulate.business.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "queue.name")
public class DirectReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener
    public void receiveDirect1(Message message) {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        System.out.println("【receiveDirect1监听到消息】" + message);
    }

//    @RabbitListener
//    public void receiveDirect2(Message message) {
//
//        System.out.println("【receiveDirect2监听到消息】" + message);
//    }
}
