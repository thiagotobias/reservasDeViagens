package br.com.reservasDeViagensFinanceiro.model.dto;

import java.util.Date;

import br.com.reservasDeViagensFinanceiro.enuns.TipoTransacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoFinanceiraDTO {
    private Long id;
    private ReservaViagemDTO reservaViagem;
    private TipoTransacao tipo;
    private Double valor;
    private Date dataTransacao;
}
