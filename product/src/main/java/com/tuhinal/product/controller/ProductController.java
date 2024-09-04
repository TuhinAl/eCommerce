package com.tuhinal.product.controller;

import com.tuhinal.product.dto.ProductDto;
import com.tuhinal.product.dto.req_dto.ProductSearchDto;
import com.tuhinal.product.services.ProductServices;
import com.tuhinal.product.utils.ApiResponse;
import com.tuhinal.product.utils.ApiResponseEntityFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductServices productServices;
    private final ApiResponseEntityFactory responseFactory;

    public ProductController(ProductServices productServices, ApiResponseEntityFactory responseFactory) {
        this.productServices = productServices;
        this.responseFactory = responseFactory;
    }


    @PostMapping()
    public ResponseEntity<ApiResponse<ProductDto>> save(@RequestBody ProductDto productDto) throws Exception {
        return responseFactory.saveResponse(productServices.save(productDto));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<ProductDto>>> getAttendance(@RequestBody ProductSearchDto productSearchDto) throws Exception {
        return responseFactory.getResponse(productServices.search(productSearchDto));
    }
}
