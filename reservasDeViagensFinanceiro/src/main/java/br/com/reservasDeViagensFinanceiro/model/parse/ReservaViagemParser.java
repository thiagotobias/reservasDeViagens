package br.com.reservasDeViagensFinanceiro.model.parse;

import br.com.reservasDeViagensFinanceiro.model.dto.ReservaViagemDTO;
import br.com.reservasDeViagensFinanceiro.model.entity.ReservaViagem;

public class ReservaViagemParser {

	public static ReservaViagem toReservaViagemEntity(ReservaViagemDTO reservaViagemDTO) {
		ReservaViagem reservaViagem = new ReservaViagem();

		return reservaViagem;
	}
	
	public static ReservaViagemDTO toReservaViagemDTO(ReservaViagem reservaViagemEntity) {
		ReservaViagemDTO reservaViagem = new ReservaViagemDTO(); 

		return reservaViagem;
	}

}
