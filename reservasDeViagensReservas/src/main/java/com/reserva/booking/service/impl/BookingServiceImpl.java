package com.reserva.booking.service.impl;

import com.reserva.booking.client.BookingClient;
import com.reserva.booking.dto.BookingDTO;
import com.reserva.booking.dto.ProductDTO;
import com.reserva.booking.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private final BookingClient bookingClient = new BookingClient();

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> resultService = bookingClient.getAllProdutsStock();
        return resultService;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        BookingDTO resultService = bookingClient.createBookingStock(bookingDTO);
        return resultService;
    }

}
