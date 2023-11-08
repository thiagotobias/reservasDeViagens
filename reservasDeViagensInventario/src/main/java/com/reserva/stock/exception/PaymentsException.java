package com.reserva.stock.exception;

public class PaymentsException extends RuntimeException {
    private static final long serialVersionUID = 23213L;

    public PaymentsException(String message) {
        super(message);
    }

}