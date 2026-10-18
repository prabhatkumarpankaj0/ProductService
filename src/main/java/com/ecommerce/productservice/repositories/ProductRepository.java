package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // is used for both crate & update
    @Override
    Product save(@NonNull Product product);

    @Override
    void delete(@NonNull Product product);

    void deleteById(@NonNull Long id);

    List<Product> findAll();

    @Override
    Optional<Product> findById(@NonNull Long id);

    //testing
    //Query methods
    List<Product> findByCategory_Name(String categoryName);

    //Queries:
    // JPA Queries
    @Query("select p " +
            "from Product p " +
            "where p.category.name = :categoryName")
    Product findByCategoryName(@Param("categoryName") String name);

    @Query("select p from Product p where p.id > :idGreaterThan")
    List<Product> findAllProductsByIdGreaterThan(@Param("idGreaterThan") Long id);

    // native queries can be used when we want to optimise our queries, like joins, first with tables with less rows
    @Query(
            value = "select * from product p join category c where p.category_id = c.id",
            nativeQuery = true
    )
    List<Product> findAllProductsByIdGreaterThan2(Long id);

    @Query(
            value = CustomeQueries.GET_PRODUCTS_WITH_CATEGORY_ID,
            nativeQuery = true
    )
    List<Product> findAllProductsByIdGreaterThan3();
}
