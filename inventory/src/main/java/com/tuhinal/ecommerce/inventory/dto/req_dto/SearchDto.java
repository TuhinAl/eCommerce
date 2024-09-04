package com.tuhinal.ecommerce.inventory.dto.req_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    private Integer page = 0;
    private Integer size = 10;
}
