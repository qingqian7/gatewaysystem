package com.cloud.gateway.filter;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//
public class HeaderCheckFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String key = "Authorizion";
        String value = exchange.getRequest().getHeaders().getFirst(key);
        if(!StringUtils.isEmpty(value) ){
            if(value.equals("consumer")){
                ServerHttpResponse response = (ServerHttpResponse) exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
