package com.reserva.stock.adapters.out.repository;

import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Modifying
    @Query(value = "update PRODUC set QUANTIDADE_DISPONIVEL = QUANTIDADE_DISPONIVEL - :availableQuantity where id = :productId", nativeQuery = true)
    void decreaseStock(@Param("productId") String productId, @Param("quantidadeReservada") Integer availableQuantity);
}


