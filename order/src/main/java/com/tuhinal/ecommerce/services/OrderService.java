package com.tuhinal.ecommerce.services;

import com.tuhinal.ecommerce.client.InventoryClient;
import com.tuhinal.ecommerce.client.req_dto.InventoryCheckDto;
import com.tuhinal.ecommerce.dto.OrderDto;
import com.tuhinal.ecommerce.dto.req_dto.OrderSearchDto;
import com.tuhinal.ecommerce.entity.Order;
import com.tuhinal.ecommerce.event.OrderPlacedEvent;
import com.tuhinal.ecommerce.repository.OrderRepository;
import com.tuhinal.ecommerce.utils.ApiResponse;
import com.tuhinal.ecommerce.utils.TransformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient,
                        KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    //todo : need to understand exception chaining in spring
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        InventoryCheckDto checkDto = new InventoryCheckDto();
        checkDto.setQuantity(orderDto.getQuantity());
        checkDto.setSkuCode(orderDto.getSkuCode());
        ApiResponse<Boolean> response = inventoryClient.isInStock(checkDto);
        Boolean inStock = response.getData();
        Order persistedOrder = new Order();
        if (inStock) {
            Order product = TransformUtil.copyProp(orderDto, Order.class);
            if(Objects.nonNull(product))
             persistedOrder = orderRepository.save(product);
            //as order is saved in db, now send the message to kafka
            //order number, email
            OrderPlacedEvent event = new OrderPlacedEvent();
            event.setOrderId(persistedOrder.getId());
            event.setEmail(orderDto.getEmail());
            event.setFirstName("Alauddin");
            event.setLastName("Tuhin");
            log.info("Start sending {} order placed event to topic.", event);
            kafkaTemplate.send("order-placed", event);
            log.info("start sending {} order placed event to topic.", event);
        }
        return TransformUtil.copyProp(persistedOrder, OrderDto.class);
    }
/*
docker-compose down
sudo rm -rf volume-data/
docker-compose build
docker-compose up -d
*/

    public Page<OrderDto> search(OrderSearchDto searchDto) {

        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize());

        Page<Order> allOrders = orderRepository.findAll(pageable);
        List<OrderDto> list = allOrders.stream().map(product -> TransformUtil.copyProp(product, OrderDto.class)).toList();
        return new PageImpl<>(list, pageable, searchDto.getSize());
    }
}
