package com.reserva.stock.adapters.out.repository.entities;


import com.reserva.stock.application.domain.Reserve;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dataReserva;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserve", fetch = FetchType.EAGER)
    private List<ProductReserveEntity> products;

    public void preparaInsert() {
        this.products.forEach(produtoReservaEntity -> produtoReservaEntity.setReserve(this));
    }

    public ReserveEntity(Reserve reserva) {
        this.products = reserva.getProdutos().stream().map(ProductReserveEntity::new).collect(Collectors.toList());
        this.dataReserva = LocalDate.now();
    }


}
