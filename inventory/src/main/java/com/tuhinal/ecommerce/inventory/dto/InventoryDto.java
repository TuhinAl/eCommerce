package com.tuhinal.ecommerce.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

    private String id;
    private String skuCode;
    private Integer quantity;
    private Boolean enabled;
}
