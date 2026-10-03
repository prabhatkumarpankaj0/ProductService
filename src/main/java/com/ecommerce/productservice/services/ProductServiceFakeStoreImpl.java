package com.ecommerce.productservice.services;

import com.ecommerce.productservice.dtos.fakeStore.FakeStoreCreateProductRequestDto;
import com.ecommerce.productservice.dtos.fakeStore.FakeStoreCreateProductResponseDto;
import com.ecommerce.productservice.dtos.fakeStore.FakeStoreSingleProductResponseDto;
import com.ecommerce.productservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class ProductServiceFakeStoreImpl implements ProductService {

    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {

        FakeStoreCreateProductRequestDto requestDto = FakeStoreCreateProductRequestDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImageUrl())
                .category(product.getCategory().getName())
                .build();

        FakeStoreCreateProductResponseDto responseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreCreateProductResponseDto.class);

        //handle case when this above api call fails or throws error or responseDto is null
        return responseDto.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id) {

        FakeStoreSingleProductResponseDto responseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreSingleProductResponseDto.class
        );

        //handle case when this above api call fails or throws error or responseDto is null
        return responseDto.toProduct();
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public List<Product> getallProducts() {

        throw new RuntimeException("Throwing before logic");
        //because of type erasure List<> will not work
//        FakeStoreSingleProductResponseDto[] responseDto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products", FakeStoreSingleProductResponseDto[].class
//        );
//
//        if (responseDto == null) {
//            return new ArrayList<>();
//        }
//
//        List<Product> products = new ArrayList<>();
//
//        for (FakeStoreSingleProductResponseDto fakeStoreSingleProductResponseDto : responseDto) {
//            products.add(
//                    Product.builder()
//                            .id(fakeStoreSingleProductResponseDto.getId())
//                            .title(fakeStoreSingleProductResponseDto.getTitle())
//                            .description(fakeStoreSingleProductResponseDto.getDescription())
//                            .price(fakeStoreSingleProductResponseDto.getPrice())
//                            .imageUrl(fakeStoreSingleProductResponseDto.getImage())
//                            .categoryName(fakeStoreSingleProductResponseDto.getCategory())
//                            .build()
//            );
//        }
//
//        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        FakeStoreSingleProductResponseDto responseDto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, FakeStoreCreateProductRequestDto.fromProduct(product), FakeStoreSingleProductResponseDto.class);
        return responseDto.toProduct();
    }
}
