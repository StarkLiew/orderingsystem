package com.example.orderingSystem.controller;

import com.example.orderingSystem.entity.Product;
import com.example.orderingSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
     private final ProductService productService;

     @Autowired
     public ProductController(ProductService productService) {
            this.productService = productService;
     }

     @GetMapping("/{productCode}")
     public Product getProducts(@PathVariable String productCode) {
         return productService.findProductByCode(productCode);
     }

}
