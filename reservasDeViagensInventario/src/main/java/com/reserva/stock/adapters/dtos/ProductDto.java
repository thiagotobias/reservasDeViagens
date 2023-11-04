package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;


@Data
public class ProductDto {
    private String id;
    private String description;
    private Integer availableQuantity;
}

