package com.reserva.stock.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.reserva.stock.adapters.dtos.*;
import com.reserva.stock.adapters.event.RabbitMQPaymentProducer;
import com.reserva.stock.adapters.out.repository.ProductRepository;
import com.reserva.stock.adapters.out.repository.ProductReserveRepository;
import com.reserva.stock.adapters.out.repository.ReserveRepository;
import com.reserva.stock.adapters.out.repository.entities.ProductEntity;
import com.reserva.stock.adapters.out.repository.entities.ProductReserveEntity;
import com.reserva.stock.adapters.out.repository.entities.ReserveEntity;
import com.reserva.stock.enums.StatusEnum;
import com.reserva.stock.exception.ProductException;
import com.reserva.stock.exception.ProductReserveException;
import com.reserva.stock.service.ReserveService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public ReserveDto reserve(ReserveDto reserveDto) throws JsonProcessingException {
        //save reserve
        ReserveEntity reserveEntity = this.saveReserve(reserveDto);
        BigDecimal total = new BigDecimal(0);

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
                total = total.add(product.getValue());
            }
        }
        sendToPayments(total, reserveDto.getReserveId());
        return reserveDto;
    }

    @Override
    @Transactional
    public void rollback(RollbackPayments rollbackPayments) {

        Optional<ReserveEntity> productsReserve = reserveRepository.findById(rollbackPayments.getIdReserve());
        if (productsReserve.isEmpty()) {
            throw new ProductReserveException("Não existe essa reserva para realizar o rollback");
        } else {
            for (ProductReserveEntity productReserveEntity : productsReserve.get().getProducts()) {
                productReserveEntity.setStatus(StatusEnum.DISPONIVEL);
                productRepository.incrementStock(productReserveEntity.getProductId(), productReserveEntity.getAvailableQuantity());
            }

          //  productReserveRepository.saveAllAndFlush(productsReserve);
        }
    }

    private void sendToPayments(BigDecimal total, Long saleId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        PaymentsDto paymentsDTO = new PaymentsDto();
        paymentsDTO.setTotalReserva(total);
        paymentsDTO.setIdReserva(saleId);
        String json = mapper.writeValueAsString(paymentsDTO);
        rabbitMQPaymentProducer.sendMessage(json);
    }

    private ReserveEntity saveReserve(ReserveDto reserveDto) {
        ReserveEntity reserveEntity = new ReserveEntity();
        reserveEntity.setDataReserva(LocalDate.parse(reserveDto.getDateReserve()));
        return reserveRepository.saveAndFlush(reserveEntity);
    }

    private void saveProductReserveEntity(ProductReserveDto productDto, ReserveEntity reserveEntity) {
        ProductReserveEntity productReserveEntity = new ProductReserveEntity();
        productReserveEntity.setId(ProductReserveEntity.ProdutoReservaID.builder().reserveId(reserveEntity.getId()).productId(productDto.getId()).build());
        productReserveEntity.setProductId(productDto.getId());
        productReserveEntity.setAvailableQuantity(productDto.getQuantity());
        productReserveEntity.setReserve(reserveEntity);
        productReserveEntity.setStatus(StatusEnum.A_CONFIRMAR);
        productReserveRepository.saveAndFlush(productReserveEntity);
    }

    public ProductEntity findByProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Não foi possivel buscar o produto com id " + id));
    }


}
