package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record ReserveDto(@JsonProperty("venda_id") String vendaId,
                         List<ProductDto> produtos,
                         @JsonProperty("data_reserva") LocalDate dataReserva) {
}
