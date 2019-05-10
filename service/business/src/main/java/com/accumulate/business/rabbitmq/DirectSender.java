package com.accumulate.business.rabbitmq;

import com.accumulate.business.config.RabbitConfig;
import com.accumulate.business.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.mg", user);
    }
}
