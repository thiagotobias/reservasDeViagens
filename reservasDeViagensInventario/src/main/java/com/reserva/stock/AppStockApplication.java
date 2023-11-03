package com.reserva.stock;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class AppStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppStockApplication.class, args);
    }

}
