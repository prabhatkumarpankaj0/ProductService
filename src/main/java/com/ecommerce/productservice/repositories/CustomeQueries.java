package com.ecommerce.productservice.repositories;

public interface CustomeQueries {
    String GET_PRODUCTS_WITH_CATEGORY_ID = "select * from product p join category c where p.category_id = c.id";
}
