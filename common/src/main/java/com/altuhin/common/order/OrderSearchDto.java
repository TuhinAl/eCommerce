package com.altuhin.common.order;

import com.altuhin.common.search.SearchDto;
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
public class OrderSearchDto extends SearchDto {

    private String id;
    private String orderNumber;
    private String skuCode;
    private Double totalPrice;
    private Integer quantity;

    public OrderSearchDto(String id) {
        this.id = id;
    }
}
