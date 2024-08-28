package com.example.orderingSystem.service;

import com.example.orderingSystem.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class RocketMQService {

    @Autowired
    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public RocketMQService(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendOrderCreatedMessage(Order order) {
        try {

            // Serialize the Order object to JSON
            String orderJson = objectMapper.writeValueAsString(order);

            // Send the message
            rocketMQTemplate.send("order-topic", MessageBuilder.withPayload(orderJson).build());

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void sendOrderStatusUpdatedMessage(Order order) {
        try {

            // Serialize the Order object to JSON
            String orderJson = objectMapper.writeValueAsString(order);

            // Send the message
            rocketMQTemplate.send("order-status-topic", MessageBuilder.withPayload(orderJson).build());

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }
}

