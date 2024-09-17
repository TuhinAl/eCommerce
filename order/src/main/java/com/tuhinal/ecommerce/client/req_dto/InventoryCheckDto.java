package com.tuhinal.ecommerce.client.req_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCheckDto {
    private String skuCode;
    private Integer quantity;
}
