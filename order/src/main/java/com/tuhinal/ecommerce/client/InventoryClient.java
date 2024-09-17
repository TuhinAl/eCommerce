package com.tuhinal.ecommerce.client;

import com.tuhinal.ecommerce.client.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "inventory", url = "http://localhost:9020")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory/check")
    ResponseEntity<ApiResponse<Boolean>> isInStock(@RequestBody InventoryCheckDto inventoryCheckDto);
}
