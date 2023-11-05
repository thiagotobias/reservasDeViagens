package com.reserva.stock.service.impl;

import com.reserva.stock.adapters.dtos.ProductDto;
import com.reserva.stock.adapters.dtos.ProductReserveDto;
import com.reserva.stock.adapters.dtos.ReserveDto;
import com.reserva.stock.adapters.event.RabbitMQPaymentProducer;
import com.reserva.stock.adapters.out.repository.ProductRepository;
import com.reserva.stock.adapters.out.repository.ProductReserveRepository;
import com.reserva.stock.adapters.out.repository.ReserveRepository;
import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import com.reserva.stock.adapters.out.repository.entities.ProductReserveEntity;
import com.reserva.stock.adapters.out.repository.entities.ReserveEntity;
import com.reserva.stock.exception.ProductException;
import com.reserva.stock.service.ReserveService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductReserveRepository productReserveRepository;

    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private RabbitMQPaymentProducer rabbitMQPaymentProducer;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Cacheable(value = "products", cacheManager = "cacheManager5Seconds")
    public List<ProductDto> getAllProducts() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("Realizou o find sem cache");
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> productsDto = Arrays.asList(modelMapper.map(products, ProductDto[].class));

        return productsDto;
    }

    @Override
    @Transactional
    public ReserveDto reserve(ReserveDto reserveDto) {
        //save reserve
        ReserveEntity reserveEntity = this.saveReserve(reserveDto);


        for (ProductReserveDto productDto : reserveDto.getProducts()) {
            //findbyProduct
            ProductEntity product = this.findByProduct(productDto.getId());

            if (product.getAvailableQuantity() < productDto.getQuantity()) {
                //quantidade maior que o disponivel
                throw new ProductException("Quantidade do produto indisponivel.");
            } else {
                //save productReserve
                this.saveProductReserveEntity(productDto, reserveEntity);
                //subtrai quantidade disponvel
                productRepository.decreaseStock(product.getId(), productDto.getQuantity());
            }
            rabbitMQPaymentProducer.sendMessage(productDto.toString());
        }
        return reserveDto;
    }

    private ReserveEntity saveReserve(ReserveDto reserveDto) {
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setDataReserva(reserveDto.getDateReserve());
        reserveEntity.setVendaId(reserveDto.getSaleId());
        return reserveRepository.saveAndFlush(reserveEntity);
    }

    private void saveProductReserveEntity(ProductReserveDto productDto, ReserveEntity reserveEntity) {
        ProductReserveEntity productReserveEntity = new ProductReserveEntity();
        productReserveEntity.setId(ProductReserveEntity.ProdutoReservaID.builder().reserveId(reserveEntity.getId()).productId(productDto.getId()).build());
        productReserveEntity.setProductId(productDto.getId());
        productReserveEntity.setAvailableQuantity(productDto.getQuantity());
        productReserveEntity.setReserve(reserveEntity);
        productReserveRepository.saveAndFlush(productReserveEntity);
    }

    public ProductEntity findByProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Não foi possivel buscar o produto com id " + id));
    }


}
