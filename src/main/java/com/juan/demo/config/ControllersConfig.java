package com.juan.demo.config;

import com.juan.demo.controller.TransacController;
import com.juan.demo.service.TransacService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfig {

    @Bean
    public TransacController transacController(TransacService transacService){
        return new TransacController(transacService);
    }
}
