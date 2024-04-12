package com.lawrencejews.orderservice.publisher;

import com.lawrencejews.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // Order-Service routing key
    @Value("${rabbitmq.binding.routing.key}")
    private String orderServiceRouting;

    // Email-Service routing key
    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailServiceRouting;

    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent ){
    LOGGER.info(String.format("Order event sent to RabbitMQ -> %s", orderEvent.toString()));

    // send an order event to order event queue
        rabbitTemplate.convertAndSend(exchange, orderServiceRouting, orderEvent);

    // send an order event to email event queue
        rabbitTemplate.convertAndSend(exchange, emailServiceRouting, orderEvent );
    }
}
