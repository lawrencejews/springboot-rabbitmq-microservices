package com.lawrencejews.springbootrabbitmqentry.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    // Exchange Configurations
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // Routing Configurations
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // RabbitMQ Template Injection
    private RabbitTemplate rabbitTemplate;

    // RabbitMQ Template Initialization
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Logger - message
    public void sendMessage(String message){
    LOGGER.info(String.format("Message sent  ->  %s", message));
    rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
