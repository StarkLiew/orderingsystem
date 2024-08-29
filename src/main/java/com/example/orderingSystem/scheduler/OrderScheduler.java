package com.example.orderingSystem.scheduler;


import com.example.orderingSystem.entity.Order;
import com.example.orderingSystem.service.OrderService;
import com.example.orderingSystem.service.RocketMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderScheduler {

    private final OrderService orderService;
    private final RocketMQService rocketMQService;

    @Autowired
    public OrderScheduler(OrderService orderService, RocketMQService rocketMQService) {
        this.orderService = orderService;
        this.rocketMQService = rocketMQService;
    }

    @Scheduled(fixedRate = 60000) // Check every 1 minutes
    public void autoClosePendingOrders() {
        List<Order> pendingOrders = orderService.findPendingOrdersOlderThan30Minutes();
        for (Order order : pendingOrders) {
            orderService.updateOrderStatus(order.getOrderNumber(), "closed");
            rocketMQService.sendOrderStatusUpdatedMessage(order);
        }
    }
}
