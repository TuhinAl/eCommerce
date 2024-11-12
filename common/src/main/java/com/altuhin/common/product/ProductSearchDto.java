package com.altuhin.common.product;

import com.altuhin.common.search.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto extends SearchDto {

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Boolean enabled;
}
