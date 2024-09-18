package com.tuhinal.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonFormat
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
