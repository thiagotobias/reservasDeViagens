package com.reserva.stock.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.nameReserva}")
    private String queueReserva;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.keyReserva}")
    private String routingKeyReserva;

    @Bean
    Queue createQueue() {
        return new Queue(queue);
    }

    @Bean
    Queue createQueueReserva() {
        return new Queue(queueReserva);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(createQueue()).to(exchange()).with(routingKey);
    }

    @Bean
    Binding bindingReserva() {
        return BindingBuilder.bind(createQueueReserva()).to(exchange()).with(routingKeyReserva);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
