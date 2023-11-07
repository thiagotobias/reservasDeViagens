package com.reserva.stock.adapters.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {


    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){

        System.out.println("Message arrived! Message: " + message);
    }
}