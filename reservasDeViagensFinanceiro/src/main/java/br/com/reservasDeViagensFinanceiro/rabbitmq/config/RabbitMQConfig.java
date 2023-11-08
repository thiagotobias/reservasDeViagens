package br.com.reservasDeViagensFinanceiro.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

    @Value("${rabbitmq.queue.estorno}")
    private String queueEstorno;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    
    @Value("${rabbitmq.routing.keyReserva}")
    private String routingKeyReserva;

    @Value("${rabbitmq.routing.keyEstorno}")
    private String routingKeyEstorno;
    
    @Bean
    Queue createQueue() {
        return new Queue(queue);
    }
    
    @Bean
    Queue createQueueReserva() {
        return new Queue(queueReserva);
    }

    @Bean
    Queue createQueueEstorno() {
        return new Queue(queueEstorno);
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
    Binding bindingEstorno() {
        return BindingBuilder.bind(createQueueEstorno()).to(exchange()).with(routingKeyEstorno);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
