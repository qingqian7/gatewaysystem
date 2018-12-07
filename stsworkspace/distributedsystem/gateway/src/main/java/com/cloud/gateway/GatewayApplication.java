package com.cloud.gateway;

import com.cloud.gateway.filter.RatelimitFilterById;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/com/**")
                        .filters(f->f.filter(new RatelimitFilterById(10,1, Duration.ofSeconds(1))))
                                .uri("lb://comsumer")
                                .order(-1000)
                                .id("comsumer_route")).build();
    }
}
