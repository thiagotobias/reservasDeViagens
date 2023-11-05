package com.reserva.booking.controller;

import com.reserva.booking.dto.BookingDTO;
import com.reserva.booking.dto.ProductBookingDTO;
import com.reserva.booking.dto.ProductDTO;
import com.reserva.booking.exception.BookingException;
import com.reserva.booking.service.impl.BookingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reservas")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductDTO> resultController = bookingService.getAllProducts();
            return ResponseEntity.ok(resultController);
        } catch (BookingException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getLocalizedMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            bookingDTO.setBookingDate(LocalDate.now());
            BookingDTO resultController = bookingService.createBooking(bookingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultController);
        } catch (BookingException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getLocalizedMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
        }
    }

}
