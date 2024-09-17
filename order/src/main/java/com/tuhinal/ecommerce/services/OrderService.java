package com.tuhinal.ecommerce.services;

import com.tuhinal.ecommerce.client.InventoryClient;
import com.tuhinal.ecommerce.client.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.dto.OrderDto;
import com.tuhinal.ecommerce.dto.req_dto.OrderSearchDto;
import com.tuhinal.ecommerce.entity.Order;
import com.tuhinal.ecommerce.repository.OrderRepository;
import com.tuhinal.ecommerce.utils.ApiResponse;
import com.tuhinal.ecommerce.utils.TransformUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    //todo : need to understand exception chaining in spring
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        InventoryCheckDto checkDto = new InventoryCheckDto();
        checkDto.setQuantity(orderDto.getQuantity());
        checkDto.setSkuCode(orderDto.getSkuCode());
        ResponseEntity<ApiResponse<Boolean>> inStock = inventoryClient.isInStock(checkDto);
        Order persistedOrder = new Order();
        if (Objects.requireNonNull(inStock.getBody()).getData()) {
            Order product = TransformUtil.copyProp(orderDto, Order.class);
            if(Objects.nonNull(product))
             persistedOrder = orderRepository.save(product);

        }
        return TransformUtil.copyProp(persistedOrder, OrderDto.class);
    }

    public Page<OrderDto> search(OrderSearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

        Page<Order> allOrders = orderRepository.findAll(pageable);
        List<OrderDto> list = allOrders.stream().map(product -> TransformUtil.copyProp(product, OrderDto.class)).toList();
        return new PageImpl<>(list, pageable, searchDto.getSize());
    }
}
