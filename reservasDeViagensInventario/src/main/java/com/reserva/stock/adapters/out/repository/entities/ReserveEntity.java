package com.reserva.stock.adapters.out.repository.entities;


import com.reserva.stock.application.domain.Reserve;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String vendaId;

    private LocalDate dataReserva;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserve", fetch = FetchType.EAGER)
    private List<ProductReserveEntity> products;

    public void preparaInsert() {
        this.products.forEach(produtoReservaEntity -> produtoReservaEntity.setReserve(this));
    }

    public ReserveEntity(Reserve reserva) {
        this.vendaId = reserva.getVendaId();
        this.products = reserva.getProdutos().stream().map(ProductReserveEntity::new).collect(Collectors.toList());
        this.dataReserva = LocalDate.now();
    }


}
