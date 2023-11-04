package com.reserva.stock.service.impl;

import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.out.repository.ProductRepository;
import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import com.reserva.stock.application.domain.Product;
import com.reserva.stock.service.ReserveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Cacheable(value = "products",cacheManager = "cacheManager5Seconds")
    public List<ProductDto> getAllProducts() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Realizou o find sem cache");
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> productsDto = Arrays.asList(modelMapper.map(products, ProductDto[].class));

        return productsDto;
    }

    @Override
    public void reserveProduct(ProductDto productDto) {

    }
}
