package br.com.reservasDeViagensFinanceiro.model.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrorMessage {

	public String message;
    private Integer statusCode;
    private List<String> erros;
    private Long timestamp;

    public ApiErrorMessage(Integer statusCode, List<String> erros) {
        super();
        this.statusCode = statusCode;
        this.erros = erros;
    }

    public ApiErrorMessage(Integer statusCode, String error) {
        super();
        this.statusCode = statusCode;
        this.erros = Arrays.asList(error);
    }
}