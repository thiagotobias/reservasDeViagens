package com.reserva.stock;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableRabbit
@EnableCaching

@SpringBootApplication
public class AppStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStockApplication.class, args);
    }
}
