package br.com.reservasDeViagensFinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reservasDeViagensFinanceiro.model.entity.ReservaViagem;

public interface ReservaViagemRepository extends JpaRepository<ReservaViagem, Long>{
}
