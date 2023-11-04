package com.reserva.stock.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUtils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
