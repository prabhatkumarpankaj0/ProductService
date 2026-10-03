package com.ecommerce.productservice.dtos.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllProductResponseDto {
    private List<SingleProductResponseDto> products;
}
