package com.accumulate.business.rabbitmq;

import com.accumulate.business.config.RabbitConfig;
import com.accumulate.business.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect1(User user) {
        System.out.println("【receiveDirect1监听到消息】" + user);
    }

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect2(User user) {

        System.out.println("【receiveDirect2监听到消息】" + user);
    }
}
