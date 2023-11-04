package com.reserva.stock.service;

import com.reserva.stock.adapters.dtos.ProductDto;

import java.util.List;

public interface ReserveService {

    List<ProductDto> getAllProducts ();

    void reserveProduct(ProductDto productDto);


}
