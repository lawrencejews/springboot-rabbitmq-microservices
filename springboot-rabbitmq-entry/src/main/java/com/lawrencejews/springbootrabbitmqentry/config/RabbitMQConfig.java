package com.lawrencejews.springbootrabbitmqentry.config;

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

    // Spring bean for rabbitmq exchange using AMQP Core
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Value("${rabbitmq.queue.name}")
    private String queue;

    // Spring bean for rabbitmq queue using AMQP Core
    @Bean
    public Queue queue(){
    return new Queue(queue);
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

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    // Spring bean for rabbitmq jsonQueue using AMQP Core
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }


    // Spring bean for rabbitmq queue using AMQP Core
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    // Binding between Queue and Exchange using Routing Key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    // RestTemplate converter to Json
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // Connection Factory

    // RabbitTemplate

    // RabbitAdmin
}
