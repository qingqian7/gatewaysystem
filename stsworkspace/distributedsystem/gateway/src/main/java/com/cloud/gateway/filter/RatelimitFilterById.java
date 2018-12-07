package com.cloud.gateway.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RatelimitFilterById implements GatewayFilter, Ordered {
    private static Logger logger = LoggerFactory.getLogger(RatelimitFilterById.class);
    private static final Map<String , Bucket> local_cache = new ConcurrentHashMap<>();
    int capacity;
    int refillToken;
    Duration refillDuration;
    public RatelimitFilterById(int capacity, int refillToken,Duration refillDuration){
        this.capacity = capacity;
        this.refillToken = refillToken;
        this.refillDuration = refillDuration;
    }

    private Bucket createNewBucket(){
        Refill refill = Refill.of(refillToken,refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity,refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = local_cache.computeIfAbsent(ip,k->createNewBucket());
        logger.info("ip: {},可用令牌数： {}",ip,bucket.getAvailableTokens());
        if(bucket.tryConsume(1)){
            return chain.filter(exchange);
        }else{
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete(); //  返回  不继续执行过滤器链
        }

    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
