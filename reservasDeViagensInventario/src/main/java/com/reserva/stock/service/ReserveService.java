package com.reserva.stock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.dtos.ReserveDto;
import com.reserva.stock.adapters.dtos.RollbackPayments;

import java.util.List;

public interface ReserveService {

    List<ProductDto> getAllProducts () throws InterruptedException;
    ReserveDto reserve(ReserveDto reserveDto) throws JsonProcessingException;
    void rollback(RollbackPayments rollbackPayments);
    void updateStatusPayments(Long aLong);
}
