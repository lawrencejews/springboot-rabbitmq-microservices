## Springboot-RabbitMQ-Microservices
RabbitMQ is a reliable and mature messaging and streaming broker, which is easy to deploy on cloud environments, on-premises, and on your local machine.`https://rabbitmq.com/tutorials`

### springboot-rabbitmq-entry
- Producer: application that sends messages not directly but through the RabbitMQ broker. 
- Consumer:application that reads messages from the RabbitMQ broker.
- Queue: buffer or storage in a RabbitMQ broker to store messages.
- Message: information sent from the producer to a consumer through RabbitMQ.
- Dependencies: spring for RabbitMQ Mesaging, spring Web and Lombok developer tools.
#### Configurations
- Run RabbitMQ management and RabbitMQ broker with docker `docker run --rm -it -p 15672:15672 -p  5672:5672 rabbitmq:3.10.5-management`
- `Spring AMQP`: provides a "template" as a high-level abstraction for sending and receiving messages.`https://spring.io/projects/spring-amqp`
- Setting for a queue requires a `message` that goes through the `exchange` where `routing binding key` is created before reaching the consumer.
- SpringBoot AutoConfiguration automatically configures these three BEANS:`ConnectionFactory`,`RabbitTemplate` and `RabbitAdmin`.
- 
