package br.com.reservasDeViagensFinanceiro.service;

import java.util.List;

import br.com.reservasDeViagensFinanceiro.model.dto.ReservaViagemDTO;
import br.com.reservasDeViagensFinanceiro.model.dto.TransacaoFinanceiraDTO;

public interface FinanceiroService {

	ReservaViagemDTO processarReserva(ReservaViagemDTO transacao);
	TransacaoFinanceiraDTO consultarTransacao(Long id);
	void processarPagamento(ReservaViagemDTO reserva);
	List<TransacaoFinanceiraDTO> consultarTransacao();
	void processarEstorno(ReservaViagemDTO reserva);
	void sendToPayments();

}
