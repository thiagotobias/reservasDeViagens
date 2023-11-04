package com.reserva.stock.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;


@Data
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 7156526077283281623L;

    private String id;
    private String description;
    private Integer availableQuantity;
}

