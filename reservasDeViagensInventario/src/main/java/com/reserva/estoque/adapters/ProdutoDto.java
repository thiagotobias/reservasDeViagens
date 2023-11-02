package com.reserva.estoque.adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProdutoDto(String id, String descricao,
                         @JsonProperty("quantidade_disponivel") Integer quantidadeDisponivel) {

}

