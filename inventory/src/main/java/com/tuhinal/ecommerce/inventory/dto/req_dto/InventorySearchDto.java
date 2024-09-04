package com.tuhinal.ecommerce.inventory.dto.req_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventorySearchDto extends SearchDto{

    private String id;
    private String skuCode;
    private Integer quantity;
    private Boolean enabled;
}
