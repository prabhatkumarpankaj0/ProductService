package com.ecommerce.productservice.dtos.fakeStore;

import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FakeStoreCreateProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product) {
        FakeStoreCreateProductRequestDto requestDto = FakeStoreCreateProductRequestDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImageUrl())
                .category(product.getCategory().getName())
                .build();
        return requestDto;
    }
}
