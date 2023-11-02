package com.reserva.stock.application.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Reserve {

    private String vendaId;
    private List<Product> produtos;
    private LocalDate dataReserva;

    public Reserve(String vendaId, List<Product> produtos) {
        this.vendaId = vendaId;
        this.produtos = produtos;
        this.dataReserva = LocalDate.now();
    }


}