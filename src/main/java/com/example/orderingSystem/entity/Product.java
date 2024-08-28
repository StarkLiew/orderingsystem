package com.example.orderingSystem.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String productCode;
    private String productName;
    private int inventoryQuantity;

}
