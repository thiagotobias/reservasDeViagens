package br.com.reservasDeViagensFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reservasDeViagensFinanceiro.model.entity.TransacaoFinanceira;

public interface TransacaoFinanceiraRepository extends JpaRepository<TransacaoFinanceira, Long>{

}
