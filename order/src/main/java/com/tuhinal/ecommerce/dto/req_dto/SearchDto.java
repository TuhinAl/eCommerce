package com.tuhinal.ecommerce.dto.req_dto;

import com.altuhin.ecommerce.entity.Auditable;
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
public class SearchDto extends Auditable {

    private Integer page;
    private Integer size;

}
