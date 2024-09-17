package com.tuhinal.ecommerce.client;

import com.tuhinal.ecommerce.client.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.utils.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(name = "inventory", url = "http://localhost:9020")
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory/check")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    ResponseEntity<ApiResponse<Boolean>> isInStock(@RequestBody InventoryCheckDto inventoryCheckDto);

    default boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skuCode {}, failure reason {}", code, throwable.getMessage());
        return false;
    }
}
