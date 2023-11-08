package com.reserva.stock.client;

import com.reserva.stock.adapters.dtos.PaymentsDto;
import com.reserva.stock.exception.PaymentsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PaymentClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payments.url}")
    private String stockUrl;

    public PaymentsDto createBookingStock(PaymentsDto paymentDto) {
        try {
            PaymentsDto resultClient = restTemplate.postForObject(stockUrl, paymentDto, PaymentsDto.class);
            return resultClient;
        } catch (RestClientException e) {
            throw new PaymentsException("Erro ao chamar pagamento " + e.getLocalizedMessage());
        }
    }
}
