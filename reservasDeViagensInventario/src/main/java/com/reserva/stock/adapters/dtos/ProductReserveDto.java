package com.reserva.stock.adapters.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductReserveDto implements Serializable {

    private static final long serialVersionUID = 7156576077283281623L;

    private String id;
    private String description;
    private String quantity;

}
