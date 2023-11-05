package br.com.reservasDeViagensFinanceiro.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String email;
    private List<ReservaViagemDTO> reservasViagens;
}
