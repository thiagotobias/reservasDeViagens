package br.com.reservasDeViagensFinanceiro.model.dto;

import java.util.Date;
import java.util.List;

import br.com.reservasDeViagensFinanceiro.enuns.TipoPagamento;
import br.com.reservasDeViagensFinanceiro.model.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaViagemDTO {
	    private Long id;
	    private Cliente cliente;
	    private Date dataReserva;
	    private String quartoHotel;
	    private Double preco;
	    private String statusPagamento;
	    private TipoPagamento tipoPagamento;
	    private List<TransacaoFinanceiraDTO> transacoesFinanceiras;

}
