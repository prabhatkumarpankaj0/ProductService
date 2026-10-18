package com.ecommerce.productservice.services;

import com.ecommerce.productservice.exceptions.ProductNotFoundException;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
// one way of telling spring, which impl should be injected is @Primary, another way is using @Qualifier in controller,
// and giving a name to service
//@Primary
public class ProductServiceDbImpl implements ProductService{

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductServiceDbImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {

        Optional<Category> category = categoryRepository.findByName(product.getCategory().getName());


        if (category.isPresent()) {
            product.setCategory(category.get());
        } else {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            Category savedCategory = categoryRepository.save(newCategory);

            // when cascade PERSIST is present in Product, it will save the category present in product,
            // while saving the product, if that category has id null.
            // Hence commented the above line.
            // we usually avoid cascade, so that in case of any issue, user can debug it and logic is written
            //Category savedCategory = newCategory;
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getSingleProduct(Long id) {

        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product not found with id " + id);
        }
        return product.get();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getallProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("No product with id: " + id);
        }

        Product productToUpdate = optionalProduct.get();

        if (product.getTitle() != null) {
            productToUpdate.setTitle(product.getTitle());
        }
        if (product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }
        if (product.getImageUrl() != null) {
            productToUpdate.setImageUrl(product.getImageUrl());
        }
        if (product.getCategory() != null) {
            String categoryName = product.getCategory().getName();
            Optional<Category> category = categoryRepository.findByName(categoryName);

            Category toBeUpdatedInProduct;
            if (category.isPresent()) {
                toBeUpdatedInProduct = category.get();
            } else {
                Category newCategory = new Category();
                newCategory.setName(categoryName);
                toBeUpdatedInProduct = categoryRepository.save(newCategory);
            }
            productToUpdate.setCategory(toBeUpdatedInProduct);
        }
        return productRepository.save(productToUpdate);
    }
}
