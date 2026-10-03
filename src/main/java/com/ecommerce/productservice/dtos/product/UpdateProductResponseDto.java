package com.ecommerce.productservice.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductResponseDto {
    private SingleProductResponseDto product;
    private String errorMessage;
}
