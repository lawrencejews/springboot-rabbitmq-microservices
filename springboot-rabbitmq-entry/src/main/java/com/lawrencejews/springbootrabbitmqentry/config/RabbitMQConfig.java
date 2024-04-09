package com.lawrencejews.springbootrabbitmqentry.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.name}")
    private String queue;

    // Spring bean for rabbitmq queue using AMQP Core
    @Bean
    public Queue queue(){
    return new Queue(queue);
    }

    // Spring bean for rabbitmq queue using AMQP Core
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    // Binding between Queue and Exchange using Routing Key
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // Connection Factory

    // RabbitTemplate

    // RabbitAdmin
}
