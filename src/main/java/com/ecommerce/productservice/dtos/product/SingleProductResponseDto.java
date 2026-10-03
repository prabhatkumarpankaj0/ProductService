package com.ecommerce.productservice.dtos.product;

import com.ecommerce.productservice.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

    public static SingleProductResponseDto fromProduct(Product product) {
        SingleProductResponseDto responseDto = new SingleProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }
}
