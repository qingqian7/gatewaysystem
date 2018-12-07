package com.cloud.gateway.config;

import com.cloud.gateway.filter.RatelimitFilterById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class ExampleConfig {
    private Logger logger = LoggerFactory.getLogger(ExampleConfig.class);
    @Value("${ratelimit.capacity}")
    int capacity;
    @Value("${ratelimit.refillToken}")
    int refillToken;
    @Value("${ratelimit.duration}")
    int duration;
    @Bean
    @Order(-1)
    public GlobalFilter a(){
        return ((exchange, chain) -> {
            logger.info("first pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("first post filter");
            }));
        });
    }

    @Bean
    @Order(0)
    public GlobalFilter b(){
        return ((exchange, chain) -> {
            logger.info("second pre filter");
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                logger.info("second post filter");
            }));
        });
    }

}
