package com.reserva.stock.service.impl;

import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.dtos.ProductReserveDto;
import com.reserva.stock.adapters.dtos.ReserveDto;
import com.reserva.stock.adapters.out.repository.ProductRepository;
import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import com.reserva.stock.exception.ProductException;
import com.reserva.stock.service.ReserveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ReserveDto reserve(ReserveDto reserveDto) {
    for(ProductReserveDto productDto: reserveDto.products()){
        ProductEntity product = this.findByProduct(productDto.getId());
        if(product.getAvailableQuantity()< Integer.parseInt(productDto.getQuantity())){
            throw new ProductException("Quantidade do produto indisponivel.");
        }

    }

        return null;
    }

    public ProductEntity findByProduct(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Não foi possivel buscar o produto com id " + id));
    }




}
