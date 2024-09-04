package com.tuhinal.ecommerce.inventory.controller;

import com.tuhinal.ecommerce.inventory.dto.InventoryDto;
import com.tuhinal.ecommerce.inventory.dto.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.inventory.dto.req_dto.InventorySearchDto;
import com.tuhinal.ecommerce.inventory.services.InventoryServices;
import com.tuhinal.ecommerce.inventory.utils.ApiResponse;
import com.tuhinal.ecommerce.inventory.utils.ApiResponseEntityFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServices inventoryServices;
    private final ApiResponseEntityFactory responseFactory;

    public InventoryController( ApiResponseEntityFactory responseFactory, InventoryServices inventoryServices) {
        this.inventoryServices = inventoryServices;
        this.responseFactory = responseFactory;
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<InventoryDto>> save(@RequestBody InventoryDto productDto) throws Exception {
        return responseFactory.saveResponse(inventoryServices.save(productDto));
    }
    @PostMapping("/check")
    public ResponseEntity<ApiResponse<Boolean>> checkInventory(@RequestBody InventoryCheckDto checkDto) throws Exception {
        return responseFactory.saveResponse(inventoryServices.checkInventory(checkDto));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<InventoryDto>>> getAttendance(@RequestBody InventorySearchDto productSearchDto) throws Exception {
        return responseFactory.getResponse(inventoryServices.search(productSearchDto));
    }
}
