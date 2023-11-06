package br.com.reservasDeViagensFinanceiro.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.reservasDeViagensFinanceiro.enuns.TipoTransacao;
import br.com.reservasDeViagensFinanceiro.exception.ReservaViagemException;
import br.com.reservasDeViagensFinanceiro.exception.TransacaoFinanceiraException;
import br.com.reservasDeViagensFinanceiro.model.dto.ReservaViagemDTO;
import br.com.reservasDeViagensFinanceiro.model.dto.TransacaoFinanceiraDTO;
import br.com.reservasDeViagensFinanceiro.model.entity.ReservaViagem;
import br.com.reservasDeViagensFinanceiro.model.entity.TransacaoFinanceira;
import br.com.reservasDeViagensFinanceiro.model.parse.ReservaViagemParser;
import br.com.reservasDeViagensFinanceiro.model.parse.TransacaoFinanceiraParser;
import br.com.reservasDeViagensFinanceiro.repository.ReservaViagemRepository;
import br.com.reservasDeViagensFinanceiro.repository.TransacaoFinanceiraRepository;
import br.com.reservasDeViagensFinanceiro.service.FinanceiroService;

@Service
public class FinanceiroServiceImpl implements FinanceiroService {

	@Autowired
	private TransacaoFinanceiraRepository transacaoRepository;
	
	@Autowired
	private ReservaViagemRepository reservaRepository;

	@Override
	public ReservaViagemDTO processarReserva(ReservaViagemDTO reserva) {
		
		ReservaViagem reservaFind = reservaRepository.findById(reserva.getId()).orElse(null) ;
		
		if(reservaFind == null) {
			reservaFind = reservaRepository.save(ReservaViagemParser.toReservaViagemEntity(reserva));
		}
		
		TransacaoFinanceira novaTransacao = new TransacaoFinanceira();
		novaTransacao.setDataTransacao(new Date());
		novaTransacao.setTipoTransacao(TipoTransacao.DEBITO);
		novaTransacao.setValor(reserva.getPreco());
		novaTransacao.setReservaViagem(reservaFind);
				
		novaTransacao = transacaoRepository.save(novaTransacao);
		
		return  ReservaViagemParser.toReservaViagemDTO(reservaRepository.findById(reservaFind.getId()).orElse(null) );
	}
	
	@Override
	public TransacaoFinanceiraDTO consultarTransacao(Long id) {
		return TransacaoFinanceiraParser.toTransacaoFinanceiraDTO(transacaoRepository.findById(id).orElseThrow(
				() -> new TransacaoFinanceiraException("Não foi possível encontrar a transação financeira.")));
	}

	@Override
	public void processarPagamento(ReservaViagemDTO reserva) {
		// Verificar o status da reserva e se há valor pendente
		ReservaViagemDTO reservaFind = ReservaViagemParser.toReservaViagemDTO( 
				 reservaRepository.findById(reserva.getId()).orElseThrow(
				() -> new ReservaViagemException("Não foi possível encontrar a reserva de viagem informada!")));
		
		if(!reserva.getPreco().equals(reservaFind.getPreco())) {
			throw new ReservaViagemException("Valor do pagamento não bate como valor da Reserva!");
		}
		
		if (reservaFind.getStatusPagamento().equals("PENDENTE")) {
			ReservaViagem reservaViagemEntity = ReservaViagemParser.toReservaViagemEntity(reservaFind);
			// Crie uma nova transação financeira
			TransacaoFinanceira transacao = new TransacaoFinanceira();
			transacao.setReservaViagem(reservaViagemEntity);
			transacao.setTipoPagamento(reservaFind.getTipoPagamento());
			transacao.setTipoTransacao(TipoTransacao.CREDITO);
			transacao.setValor(reservaFind.getPreco());
			transacao.setDataTransacao(new Date());

			// Salve a transação no banco de dados
			transacaoRepository.save(transacao);

			// Atualize o status da reserva de viagem
			reservaFind.setStatusPagamento("PAGO");
			reservaRepository.save(reservaViagemEntity);

		}
	}


	@Override
	public List<TransacaoFinanceiraDTO> consultarTransacao() {
		return transacaoRepository.findAll().stream().map(TransacaoFinanceiraParser::toTransacaoFinanceiraDTO).collect(Collectors.toList()) ;
	}
}
