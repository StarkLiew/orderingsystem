package com.example.orderingSystem.service;

import com.example.orderingSystem.entity.Order;
import com.example.orderingSystem.entity.Product;
import com.example.orderingSystem.respository.OrderRepository;
import com.example.orderingSystem.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RedisLockService redisLockService;
    private final RocketMQService rocketMQService;


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, RedisLockService redisLockService, RocketMQService rocketMQService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.redisLockService = redisLockService;
        this.rocketMQService = rocketMQService;
    }

    public void placeOrder(Long userId, String productCode, int quantity) {
        String lockKey = "lock:product:" + productCode;

        if (redisLockService.acquireLock(lockKey)) {

            try {
                Product product = productRepository.findByProductCode(productCode);
                if (product == null) {
                    throw new IllegalStateException("Product not available");
                }

                if (product.getInventoryQuantity() < quantity) {
                    throw new IllegalStateException("Product insufficient inventory");
                }

                int updatedRows = productRepository.reduceInventory(productCode, quantity);
                if (updatedRows == 0) {
                    throw new IllegalStateException("Failed to update inventory, please try again");
                }

                Order order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString());
                order.setUserId(userId);
                order.setProductCode(productCode);
                order.setDataVolume(quantity);
                order.setAmount(BigDecimal.valueOf(100)); // Assume fixed price for simplicity
                order.setTotalAmount(order.getAmount().multiply(BigDecimal.valueOf(quantity)));
                order.setOrderStatus("pending");
                order.setCreatedAt(LocalDateTime.now());
                order.setUpdatedAt(LocalDateTime.now());

                orderRepository.insertOrder(order);
                rocketMQService.sendOrderCreatedMessage(order);

            } finally {
                redisLockService.releaseLock(lockKey);
            }
        } else {
            throw new IllegalStateException("Failed to acquire lock, please try again");
        }

    }

    public void updateOrderStatus(String orderNumber, String status) {
        orderRepository.updateOrderStatus(orderNumber, status);
    }

    public Order findOrder(String orderNumber) {
        return orderRepository.findOrderByOrderNumber(orderNumber);
    }

    public List<Order> findPendingOrdersOlderThan30Minutes() {
        return orderRepository.findPendingOrdersOlderThan30Minutes();
    }
}
