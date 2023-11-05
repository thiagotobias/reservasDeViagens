package br.com.reservasDeViagensFinanceiro.model.entity;

import java.util.Date;

import br.com.reservasDeViagensFinanceiro.enuns.TipoPagamento;
import br.com.reservasDeViagensFinanceiro.enuns.TipoTransacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransacaoFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_viagem_id")
    private ReservaViagem reservaViagem;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    
    private Double valor;
    private Date dataTransacao;

}

