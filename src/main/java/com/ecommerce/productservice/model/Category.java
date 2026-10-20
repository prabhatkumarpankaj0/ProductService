package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category extends BaseModel {
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> featuredProducts;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Product> products;
    private Integer countOfProducts;
}
