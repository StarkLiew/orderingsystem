package com.example.orderingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private Long id;
    private String orderNumber;
    private Long userId;
    private String productCode;
    private int dataVolume;
    private BigDecimal amount;
    private BigDecimal totalAmount;
    private String orderStatus; // e.g., "pending", "paid", "closed"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
