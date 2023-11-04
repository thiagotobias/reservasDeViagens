package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record ReserveDto(@JsonProperty("venda_id") String saleId,
                         List<ProductReserveDto> products,
                         @JsonProperty("data_reserva")
                         @JsonFormat(pattern="dd-MM-yyyy")
                         LocalDate dateReserve) {
}
