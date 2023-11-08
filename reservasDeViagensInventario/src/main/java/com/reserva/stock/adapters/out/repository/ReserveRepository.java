package com.reserva.stock.adapters.out.repository;

import com.reserva.stock.adapters.out.repository.entities.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

}
