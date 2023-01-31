package com.prabhat.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "stock")
public class ProductController {

    private final ProductService productService;

    private Order order;

    @Autowired
    public ProductController(ProductService productService, Order order) {
        this.productService = productService;
        this.order = order;
    }

    @GetMapping
    public List<Stock> getAllStock(){

        return productService.getAllStock();
    }

    @GetMapping(path = "{id}")
    public Stock getStock(@PathVariable("id") int id){

        return productService.getStock(id);
    }

    @GetMapping(path = "{userId}")
    public List<Stock> getOrder(@PathVariable("userId")int userId){

        return productService.getOrder(userId);
    }

    @PostMapping
    public void addStock(@RequestBody Stock stock){
        productService.addStock(stock);
    }

    @PostMapping(path="{productId}/{userId}/order")
    public void placeOrder(@PathVariable("productId")int productId, @PathVariable("userId")int userId,
                           @RequestParam(required = true) int orderQuantity){
        order.setUserId(userId);
        order.setProductId(productId);
        order.setOrderQuantity(orderQuantity);

        productService.addOrder(order);
    }
}
