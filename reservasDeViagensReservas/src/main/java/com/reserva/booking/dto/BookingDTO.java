package com.reserva.booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {

    private String bookingId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bookingDate;
    private List<ProductBookingDTO> products;

}
