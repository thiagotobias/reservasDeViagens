package com.reserva.stock.service;

import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.dtos.ReserveDto;

import java.util.List;

public interface ReserveService {

    List<ProductDto> getAllProducts () throws InterruptedException;

    ReserveDto reserve(ReserveDto reserveDto);


}
