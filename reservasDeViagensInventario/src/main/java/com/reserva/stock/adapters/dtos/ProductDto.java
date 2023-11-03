package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDto(String id, String description,
                         @JsonProperty("quantidade_disponivel") Integer availableQuantity
) {

}

