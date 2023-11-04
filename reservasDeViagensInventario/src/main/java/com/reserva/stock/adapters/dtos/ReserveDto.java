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

    @JsonProperty("venda_id")
    private String saleId;
    private List<ProductReserveDto> products;
    @JsonProperty("data_reserva")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateReserve;
}
