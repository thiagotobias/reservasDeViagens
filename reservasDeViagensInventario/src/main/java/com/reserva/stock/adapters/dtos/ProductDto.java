package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDto(String id, String descricao,
                         @JsonProperty("quantidade_disponivel") Integer quantidadeDisponivel) {

}

