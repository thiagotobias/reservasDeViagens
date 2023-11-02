package com.reserva.estoque.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record ReservaDto(@JsonProperty("venda_id") String vendaId,
                         List<ProdutoDto> produtos,
                         @JsonProperty("data_reserva") LocalDate dataReserva) {
}
