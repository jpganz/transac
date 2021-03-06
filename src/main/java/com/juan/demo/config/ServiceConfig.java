package com.juan.demo.config;

import com.hazelcast.core.HazelcastInstance;
import com.juan.demo.repository.TransacRepository;
import com.juan.demo.service.TransacService;
import com.juan.demo.service.TransacServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public TransacService transacService(TransacRepository transacRepository, HazelcastInstance hazelcastInstance) {
        return new TransacServiceImpl(transacRepository, hazelcastInstance);
    }
}
