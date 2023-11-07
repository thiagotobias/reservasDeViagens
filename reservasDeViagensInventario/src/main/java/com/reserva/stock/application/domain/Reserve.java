package com.reserva.stock.application.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reserve {

    private Long vendaId;
    private List<Product> produtos;
    private LocalDate dataReserva;

    public Reserve(Long vendaId, List<Product> produtos) {
        this.vendaId = vendaId;
        this.produtos = produtos;
        this.dataReserva = LocalDate.now();
    }


}