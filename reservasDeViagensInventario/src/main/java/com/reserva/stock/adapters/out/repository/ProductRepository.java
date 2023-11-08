package com.reserva.stock.adapters.out.repository;

import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Modifying
    @Query(value = "update PRODUTO set QUANTIDADE_DISPONIVEL = QUANTIDADE_DISPONIVEL - :quantidadeReservada where id = :productId", nativeQuery = true)
    void decreaseStock(@Param("productId") String productId, @Param("quantidadeReservada") Integer quantidadeReservada);


    @Modifying
    @Query(value = "update PRODUTO set QUANTIDADE_DISPONIVEL = QUANTIDADE_DISPONIVEL + :quantidadeEstorno where id = :productId", nativeQuery = true)
    void incrementStock(@Param("productId") String productId, @Param("quantidadeReservada") Integer quantidadeEstorno);
}


