package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // is used for both crate & update
    @Override
    Product save(Product product);

    @Override
    void delete(Product product);

    List<Product> findAll();
}
