package com.juan.demo.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import( {HibernateJpaAutoConfiguration.class})

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.juan.demo.repository")
@EntityScan(basePackages = "com.juan.demo")
public class DataConfig {

}
