package com.reserva.booking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBookingDTO {

    private String id;
    private String description;
    private String quantity;

}
