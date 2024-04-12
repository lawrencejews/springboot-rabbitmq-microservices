package com.lawrencejews.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Spring Bean for Exchange
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    // Spring Bean for Queue - Order queue
    @Value("${rabbitmq.queue.order.name}")
    private String orderQueue;
    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    // Spring Bean for binding between Exchange and Queue using Routing key
    @Value("${rabbitmq.binding.routing.key}")
    private String order_routing_key;
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(exchange())
                .with(order_routing_key);
    }

    // Spring Bean for Queue - Email queue
    @Value("${rabbitmq.queue.email.name}")
    private String emailQueue;
    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }

    // Spring Bean for binding between Exchange and Queue using Routing key
    @Value("${rabbitmq.binding.email.routing.key}")
    private String email_routing_key;
    @Bean
    public Binding emailBinding(){
        return BindingBuilder
                .bind(emailQueue())
                .to(exchange())
                .with(email_routing_key);
    }


    // Message Converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    // Configure RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate (ConnectionFactory connectionFactory){

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
