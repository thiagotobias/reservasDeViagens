package br.com.reservasDeViagensFinanceiro.model.entity;

import java.util.Date;
import java.util.List;

import br.com.reservasDeViagensFinanceiro.enuns.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReservaViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date dataReserva;
    private String quartoHotel;
    private Double preco;
    private String statusPagamento;
    
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    @OneToMany(mappedBy = "reservaViagem")
    private List<TransacaoFinanceira> transacoesFinanceiras;

}

