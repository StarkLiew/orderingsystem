package com.example.orderingSystem.service;

import com.example.orderingSystem.entity.Product;
import com.example.orderingSystem.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "#productCode")
    public Product findProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }


}
