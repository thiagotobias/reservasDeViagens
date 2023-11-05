package br.com.reservasDeViagensFinanceiro.model.parse;

import br.com.reservasDeViagensFinanceiro.model.dto.TransacaoFinanceiraDTO;
import br.com.reservasDeViagensFinanceiro.model.entity.TransacaoFinanceira;

public class TransacaoFinanceiraParser {

	public static TransacaoFinanceira toTransacaoFinanceiraEntity(TransacaoFinanceiraDTO transacaoDTO) {
		TransacaoFinanceira transacaoFinanceira = new TransacaoFinanceira();
		
		return transacaoFinanceira;
	}

	public static TransacaoFinanceiraDTO toTransacaoFinanceiraDTO(TransacaoFinanceira TransacaoEntity) {
		TransacaoFinanceiraDTO transacaoFinanceira = new TransacaoFinanceiraDTO();
		
		return transacaoFinanceira;
	}

}
