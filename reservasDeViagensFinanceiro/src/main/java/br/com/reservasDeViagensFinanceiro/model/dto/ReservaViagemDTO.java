package br.com.reservasDeViagensFinanceiro.model.dto;

import java.util.List;

import br.com.reservasDeViagensFinanceiro.enuns.StatusPagamento;
import br.com.reservasDeViagensFinanceiro.enuns.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaViagemDTO {
	
	    private String idReserva;
	    private Double totalReserva;
	    private StatusPagamento statusPagamento;
	    private TipoPagamento tipoPagamento;
	    private List<TransacaoFinanceiraDTO> transacoesFinanceiras;

}
