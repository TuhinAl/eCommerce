package com.tuhinal.ecommerce.client.req_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdatedCheckDto extends InventoryCheckDto {
    private Throwable throwable;
}
