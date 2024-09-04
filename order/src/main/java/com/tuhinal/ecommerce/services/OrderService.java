package com.tuhinal.ecommerce.services;

import com.tuhinal.ecommerce.dto.OrderDto;
import com.tuhinal.ecommerce.dto.req_dto.OrderSearchDto;
import com.tuhinal.ecommerce.entity.Order;
import com.tuhinal.ecommerce.repository.OrderRepository;
import com.tuhinal.ecommerce.utils.TransformUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional
    public OrderDto save(OrderDto orderDto) {
        Order product = TransformUtil.copyProp(orderDto, Order.class);
        Order persistedOrder = orderRepository.save(product);
        return TransformUtil.copyProp(persistedOrder, OrderDto.class);
    }

    public Page<OrderDto> search(OrderSearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

        Page<Order> allOrders = orderRepository.findAll(pageable);
        List<OrderDto> list = allOrders.stream().map(product -> TransformUtil.copyProp(product, OrderDto.class)).toList();
        return new PageImpl<>(list, pageable, searchDto.getSize());
    }
}
