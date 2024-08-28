package com.example.orderingSystem.controller;

import com.example.orderingSystem.entity.Order;
import com.example.orderingSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
     private final OrderService orderService;

     @Autowired
     public OrderController(OrderService orderService) {
            this.orderService = orderService;
     }


    @PostMapping("/create")
    public String createOrder(@RequestParam Long userId, @RequestParam String productCode, @RequestParam int quantity) {
        orderService.placeOrder(userId, productCode, quantity);
        return "Order placed successfully";
    }

    @GetMapping("/{orderNumber}")
    public Order getOrder(@PathVariable String orderNumber) {
        return orderService.findOrder(orderNumber);
    }

    @PostMapping("/update")
    public String updateOrderStatus(@RequestParam String orderNumber, @RequestParam String status) {
        orderService.updateOrderStatus(orderNumber, status);
        return "Order status updated successfully";
    }

}
