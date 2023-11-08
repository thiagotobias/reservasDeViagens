package com.reserva.stock.adapters.out.repository;


import com.reserva.stock.adapters.out.repository.entities.ProductReserveEntity;
import com.reserva.stock.adapters.out.repository.entities.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReserveRepository extends JpaRepository<ProductReserveEntity, String> {
    @Query(value = "SELECT p FROM ProductReserveEntity p WHERE p.reserve.id = :reserveId")
     List<ProductReserveEntity> findByReserve(Long reserveId);


}
