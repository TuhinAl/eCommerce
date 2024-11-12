package com.altuhin.common.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)

public class OrderDto {

    private String id;
    private String orderNumber;
    private String email;
    private String skuCode;
    private Double totalPrice;
    private Integer quantity;

    public OrderDto(String id) {
        this.id = id;
    }
}
