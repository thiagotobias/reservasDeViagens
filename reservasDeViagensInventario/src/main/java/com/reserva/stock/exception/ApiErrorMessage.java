package com.reserva.stock.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorMessage {

    private HttpStatusCode status;
    private List<String> erros;

    public ApiErrorMessage(HttpStatusCode status, List<String> erros) {
        super();
        this.status = status;
        this.erros = erros;
    }

    public ApiErrorMessage(HttpStatusCode status, String error) {
        super();
        this.status = status;
        this.erros = Arrays.asList(error);
    }
}