package com.reserva.stock.adapters.out.repository.entities;

import com.reserva.stock.application.domain.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "quantidade_disponivel")
    private Integer availableQuantity;

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.availableQuantity = product.getAvailableQuantity();
    }


}
