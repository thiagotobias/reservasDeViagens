package com.reserva.stock.exception;

public class ProductReserveException extends RuntimeException {
    private static final long serialVersionUID = 2321L;

    public ProductReserveException(String message) {
        super(message);
    }

}