package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.dtos.product.*;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

//    @Value("${productService.type}")
//    private String productServiceType;

    private ProductService productService;

    public ProductController(@Qualifier("dbProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }

//    @GetMapping("")
//    public List<SingleProductResponseDto> getAllProducts() {
//        List<Product> products = productService.getallProducts();
//        return products.stream()
//                .map(
//                        SingleProductResponseDto::fromProduct
//                )
//                .collect(Collectors.toList());
//    }

    @GetMapping("")
    public GetAllProductResponseDto getAllProducts() {
        List<Product> products = productService.getallProducts();

        List<SingleProductResponseDto> productResponseDtos = products.stream()
                .map(
                        product -> SingleProductResponseDto.fromProduct(product)
                )
                .toList();

        GetAllProductResponseDto response  =  new GetAllProductResponseDto();
        response.setProducts(productResponseDtos);
        return  response;
    }

    @GetMapping("/{id}")
    public SingleProductResponseDto getSingleProduct(@PathVariable("id") Long id) {
        Product product = productService.getSingleProduct(id);
        return SingleProductResponseDto.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public UpdateProductResponseDto updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequestDto request) {
        Product product = productService.updateProduct(id, request.toProduct());
        UpdateProductResponseDto response  =  new UpdateProductResponseDto();
        response.setProduct(SingleProductResponseDto.fromProduct(product));
        return response;
    }

    public void replaceProduct() {}

    @GetMapping("/magic")
    public String magic(){
        return "Magic";
    }

}
