package com.reserva.stock.adapters.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class ConsumerPaymentsQueue {

    @RabbitListener(queues = {"${rabbitmq.queue.nameReserva}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
    }
}
