package com.reserva.stock.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private String id;
    private String descricao;
    private Integer quantidadeDispinvel;

    public Product(String id, String descricao, Integer quantidadeDispinvel) {
        this.id = id;
        this.descricao = descricao;
        this.quantidadeDispinvel = quantidadeDispinvel;
    }
}
