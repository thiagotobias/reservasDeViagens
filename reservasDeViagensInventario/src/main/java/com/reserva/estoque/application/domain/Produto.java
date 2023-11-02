package com.reserva.estoque.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Produto {

    private String id;
    private String descricao;
    private Integer quantidadeDispinvel;

    public Produto(String id, String descricao, Integer quantidadeDispinvel) {
        this.id = id;
        this.descricao = descricao;
        this.quantidadeDispinvel = quantidadeDispinvel;
    }
}
