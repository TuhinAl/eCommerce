package com.tuhinal.ecommerce.controller;


import com.tuhinal.ecommerce.dto.OrderDto;
import com.tuhinal.ecommerce.dto.req_dto.OrderSearchDto;
import com.tuhinal.ecommerce.services.OrderService;
import com.tuhinal.ecommerce.utils.ApiResponse;
import com.tuhinal.ecommerce.utils.ApiResponseEntityFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final ApiResponseEntityFactory responseFactory;

    public OrderController(OrderService orderService, ApiResponseEntityFactory responseFactory) {
        this.orderService = orderService;
        this.responseFactory = responseFactory;
    }


    @PostMapping("/place")
    public ResponseEntity<ApiResponse<OrderDto>> placeOrder(@RequestBody OrderDto productDto) throws Exception {
        return responseFactory.saveResponse(orderService.placeOrder(productDto));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<OrderDto>>> getAttendance(@RequestBody OrderSearchDto orderSearchDto) throws Exception {
        return responseFactory.getResponse(orderService.search(orderSearchDto));
    }
}
