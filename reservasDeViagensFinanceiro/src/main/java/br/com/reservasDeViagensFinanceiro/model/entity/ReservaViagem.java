package br.com.reservasDeViagensFinanceiro.model.entity;

import java.util.List;

import br.com.reservasDeViagensFinanceiro.enuns.StatusPagamento;
import br.com.reservasDeViagensFinanceiro.enuns.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReservaViagem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idReserva;

    private Double totalReserva;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @OneToMany(mappedBy = "reservaViagem")
    private List<TransacaoFinanceira> transacoesFinanceiras;

}

