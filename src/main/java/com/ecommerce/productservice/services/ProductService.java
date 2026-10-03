package com.ecommerce.productservice.services;

import com.ecommerce.productservice.model.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);

    public Product getSingleProduct(Long id);

    public void deleteProduct(Long id);

    public List<Product> getallProducts();

    public Product updateProduct(Long id, Product product);
}
