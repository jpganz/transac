package com.juan.demo.config;

import com.hazelcast.config.CollectionConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;

@Configuration
public class CacheConfig {

    @Bean
    HazelcastInstance hazelCastInstance(){

        Config cfg = new Config();
        cfg.getMapConfig("transacs")
           .setTimeToLiveSeconds(60)
           .setMaxIdleSeconds(60);
        return Hazelcast.newHazelcastInstance(cfg);
    }
}
