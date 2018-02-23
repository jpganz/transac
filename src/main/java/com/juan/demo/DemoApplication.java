package com.juan.demo;

import com.juan.demo.config.CacheConfig;
import com.juan.demo.config.ControllersConfig;
import com.juan.demo.config.DataConfig;
import com.juan.demo.config.ServiceConfig;
import com.juan.demo.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import( {
                 DataConfig.class,
                 ControllersConfig.class,
                 ServiceConfig.class,
                 SwaggerConfig.class,
                 CacheConfig.class
         })
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }
}
