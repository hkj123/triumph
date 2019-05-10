package com.accumulate.business.rabbitmq;

import com.accumulate.business.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        for (int i = 0; i < 100; i++) {
            String msg = "hello, 序号: " + i;
            System.out.println("Producer, " + msg);
            this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.TEST_TOPIC_ROUTINGKEY, msg);
        }
    }
}
