package com.reserva.stock.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private String id;
    private String description;
    private Integer availableQuantity;

    public Product(String id, String description, Integer availableQuantity) {
        this.id = id;
        this.description = description;
        this.availableQuantity = availableQuantity;
    }
}
