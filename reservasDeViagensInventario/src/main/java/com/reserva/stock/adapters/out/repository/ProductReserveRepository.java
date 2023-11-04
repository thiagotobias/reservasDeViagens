package com.reserva.stock.adapters.out.repository;


import com.reserva.stock.adapters.out.repository.entities.ProductReserveEntity;
import com.reserva.stock.adapters.out.repository.entities.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReserveRepository extends JpaRepository<ProductReserveEntity, String> {
}
