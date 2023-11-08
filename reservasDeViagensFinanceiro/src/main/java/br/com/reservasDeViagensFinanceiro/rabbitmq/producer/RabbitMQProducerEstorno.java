package br.com.reservasDeViagensFinanceiro.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerEstorno {
	
	@Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.keyEstorno}")
    private String keyEstorno;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(
                exchange, keyEstorno, message);
    }
}
