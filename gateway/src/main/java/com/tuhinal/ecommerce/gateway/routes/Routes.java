package com.tuhinal.ecommerce.gateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class Routes {
    /**
     * Functional Endpoint Programming Model
     */

    @Bean
    public RouterFunction<ServerResponse> productServiceSearchRoute() {
        return GatewayRouterFunctions
                .route("product_service")
                .route(RequestPredicates.path("/api/product/search"), HandlerFunctions.http("http://localhost:9010"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSaveRoute() {
        return GatewayRouterFunctions
                .route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:9010"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSaveRoute() {
        return GatewayRouterFunctions
                .route("order_service")
                .route(RequestPredicates.path("/api/order/place"), HandlerFunctions.http("http://localhost:9020"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> orderServiceSearchRoute() {
        return GatewayRouterFunctions
                .route("order_service")
                .route(RequestPredicates.path("/api/order/search"), HandlerFunctions.http("http://localhost:9020"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return GatewayRouterFunctions
                .route("inventory_service")
                .route(RequestPredicates.path("/api/inventory/check"), HandlerFunctions.http("http://localhost:9030"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker",
                        URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return GatewayRouterFunctions
                .route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service is Unavailable, Please try again later."))
                .build();
    }
}

//todo docker file and folder mount and permission related problems
/**
 * check owner: ls -ld ./docker/tempo/
 * 	output: drwxr-xr-x 2 root root 4096 Sep 17 12:34 ./docker/tempo/
 *
 * change ownership: sudo chown -R bjit:bjit ./docker/tempo/
 * change permission: sudo chmod -R 755 ./docker/tempo/
 * verify and Try agian:   touch ./docker/tempo/tempo.yml
 * 						nano ./docker/tempo/tempo.yml
 */

/**
 * check owner: ls -ld ./docker/grafana/
 * 	output: drwxr-xr-x 2 root root 4096 Sep 17 12:34 ./docker/grafana/
 *
 * change ownership: sudo chown -R bjit:bjit ./docker/grafana/
 * change permission: sudo chmod -R 755 ./docker/grafana/
 * verify and Try agian:   touch ./docker/grafana/datasource.yml
 * 						nano ./docker/tempo/tempo.yml
 */