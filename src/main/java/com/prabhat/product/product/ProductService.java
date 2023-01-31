package com.prabhat.product.product;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Stock> getAllStock(){

        return productRepository.findAll();
    }
    public Stock getStock(int id){

        Optional<Stock> stockOptional= productRepository.findById(id);

        if(!stockOptional.isPresent()){
            throw new IllegalStateException("no product with this product i.d.");
        }

        return stockOptional.get();
    }

    public List<Stock> getOrder(int userId){

        List<Order> orderList = orderRepository.findOrderByUserId(userId);

        List<Integer> productIdList= new ArrayList<>();

        for(Order k : orderList){
            productIdList.add(k.getProductId());
        }

        List<Stock> userOrder = new ArrayList<>();

        for(int k : productIdList){

            userOrder.add(productRepository.findById(k).get());
        }
        return userOrder;
    }

    public void addStock(Stock stock){

        productRepository.save(stock);
    }
    public void addOrder(Order order){

        Optional<Stock> stockOptional= productRepository.findById(order.getProductId());

        if(!stockOptional.isPresent()){
            throw new IllegalStateException("no such product");
        }

        Stock stock= stockOptional.get();

        if(order.getOrderQuantity()>stock.getQuantity()){
            throw new IllegalStateException("not enough quantity in stock");
        }

        orderRepository.save(order);

        stock.setQuantity(stock.getQuantity()-order.getOrderQuantity());
    }
}
