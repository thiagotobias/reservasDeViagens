package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDto {

    @JsonProperty("bookingId")
    private Long reserveId;
    private List<ProductReserveDto> products;
    @JsonProperty("bookingDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private String dateReserve;
}
