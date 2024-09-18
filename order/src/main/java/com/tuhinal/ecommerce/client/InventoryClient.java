package com.tuhinal.ecommerce.client;

import com.tuhinal.ecommerce.client.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.client.req_dto.InventoryUpdatedCheckDto;
import com.tuhinal.ecommerce.utils.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

//@FeignClient(name = "inventory", url = "http://localhost:9020")
public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @PostExchange("/api/inventory/check")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    ApiResponse<Boolean> isInStock(@RequestBody InventoryCheckDto inventoryCheckDto);

    default ApiResponse<Boolean> fallbackMethod(InventoryCheckDto inventoryCheckDto, Throwable throwable) {
        log.info("Cannot get inventory for skuCode {}, failure reason {}", inventoryCheckDto.getSkuCode(),
                throwable.getMessage());
        return new ApiResponse<>(false, null, null,null,null);
    }
}
