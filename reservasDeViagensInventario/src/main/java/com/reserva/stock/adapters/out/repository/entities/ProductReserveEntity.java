package com.reserva.stock.adapters.out.repository.entities;

import com.reserva.stock.application.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "produto_reserva")
@Data
@NoArgsConstructor
public class ProductReserveEntity {


    @EmbeddedId
    private ProdutoReservaID id;

    @Column(name = "produto_id", insertable = false, updatable = false)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "reserva_id", insertable = false, updatable = false)
    private ReserveEntity reserve;

    private Integer availableQuantity;

    @PrePersist
    private void montaPK() {
        this.id = new ProdutoReservaID(this.productId, this.reserve.getId());
    }
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ProdutoReservaID implements Serializable {

        @Column(name = "produto_id")
        private String productId;

        @Column(name = "reserva_id")
        private String reserveId;
    }

    public ProductReserveEntity(Product product) {
        this.productId = product.getId();
        this.availableQuantity = product.getAvailableQuantity();
    }


}
