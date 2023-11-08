package br.com.reservasDeViagensFinanceiro.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.reservasDeViagensFinanceiro.model.dto.ReservaViagemDTO;
import br.com.reservasDeViagensFinanceiro.service.FinanceiroService;

@Service
public class RabbitMQConsumer {
	
	@Autowired
    private FinanceiroService financeiroService;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){
    	System.out.println("Message arrived! Message: " + message);
    	ReservaViagemDTO reserva = new Gson().fromJson(message, ReservaViagemDTO.class);
    	
    	financeiroService.processarReserva(reserva);
    }
}
