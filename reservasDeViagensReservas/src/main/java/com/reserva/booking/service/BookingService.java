package com.reserva.booking.service;

import com.reserva.booking.dto.BookingDTO;
import com.reserva.booking.dto.ProductDTO;

import java.util.List;

public interface BookingService {

    List<ProductDTO> getAllProducts ();

    BookingDTO createBooking(BookingDTO bookingDTO);


}
