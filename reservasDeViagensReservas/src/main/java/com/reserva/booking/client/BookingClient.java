package com.reserva.booking.client;

import com.reserva.booking.dto.BookingDTO;
import com.reserva.booking.dto.ProductDTO;
import com.reserva.booking.exception.BookingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class BookingClient {

    @Autowired
    private RestTemplate restTemplate = null;

    @Value("${stock.url}")
    private String stockUrl;

    public List<ProductDTO> getAllProdutsStock() {
        try {
            List<ProductDTO> resultClient = restTemplate.getForObject(stockUrl, List.class);
            return resultClient;
        } catch (RestClientException e) {
            throw new BookingException("Erro ao buscar produtos no sistema de inventário: " + e.getLocalizedMessage());
        }
    }

    public BookingDTO createBookingStock(BookingDTO bookingDTO) {
        try {
            BookingDTO resultClient = restTemplate.postForObject(stockUrl, bookingDTO, BookingDTO.class);
            return resultClient;
        } catch (RestClientException e) {
            throw new BookingException("Erro ao criar reserva no sistema de inventário: " + e.getLocalizedMessage());
        }
    }

}