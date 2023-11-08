package com.reserva.stock.adapters.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RollbackPayments {

    @JsonProperty("id")
    private Long idReserve;
}
