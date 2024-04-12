## Springboot-RabbitMQ-Microservices
RabbitMQ is a reliable and mature messaging and streaming broker, which is easy to deploy on cloud environments, on-premises, and on your local machine.`https://rabbitmq.com/tutorials`

### springboot-rabbitmq-entry
- Producer: application that sends messages not directly but through the RabbitMQ broker. 
- Consumer:application that reads messages from the RabbitMQ broker.
- Queue: buffer or storage in a RabbitMQ broker to store messages.
- Message: information sent from the producer to a consumer through RabbitMQ.
- Dependencies: spring for RabbitMQ Messaging, spring Web and Lombok developer tools.
#### Configurations
- Run RabbitMQ management and RabbitMQ broker with docker `docker run --rm -it -p 15672:15672 -p  5672:5672 rabbitmq:3.10.5-management`
- `Spring AMQP`: provides a `template` as a high-level abstraction for sending and receiving messages.`https://spring.io/projects/spring-amqp`
- Setting for a queue requires a `message` that goes through the `exchange` where `routing binding key` is created before reaching the consumer.
- `SpringBoot AutoConfiguration` automatically configures these three BEANS:`ConnectionFactory`,`RabbitTemplate` and `RabbitAdmin`.
#### Apache Kafka Use Cases
- Apache Kafka -> designed to handle large volumes of data in real time and supports features like event streaming, message replay, and distributed processing. 
- Apache Kafka is commonly used for big data applications, IoT, and real-time analytics.
#### RabbitMQ Use Cases
- Used for multiple messaging protocols and provides features like message queuing, routing, and delivery guarantees. 
- RabbitMQ is commonly used in enterprise environments for mission-critical applications that require high availability and fault tolerance. 
