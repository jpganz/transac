package com.juan.demo.config;

import com.juan.demo.repository.TransacRepository;
import com.juan.demo.service.TransacService;
import com.juan.demo.service.TransacServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public TransacService transacService(TransacRepository transacRepository){
        return new TransacServiceImpl(transacRepository);
    }
}
