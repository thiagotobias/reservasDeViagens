package com.reserva.stock.adapters.in;

import com.reserva.stock.service.ReserveService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ConsumerPaymentsQueue {

    @Autowired
    private ReserveService reserveService;
    @RabbitListener(queues = {"${rabbitmq.queue.nameReserva}"})
    public void receive(@Payload String reserveId) {
        System.out.println("Message " + reserveId);
        reserveService.updateStatusPayments(Long.valueOf(reserveId));
    }
}
