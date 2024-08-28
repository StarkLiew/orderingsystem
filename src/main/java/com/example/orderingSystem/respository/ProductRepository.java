package com.example.orderingSystem.respository;

import com.example.orderingSystem.entity.Product;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductRepository {

    @Select("SELECT * FROM products WHERE product_code = #{productCode}")
    Product findByProductCode(@Param("productCode") String productCode);

    @Update("UPDATE products SET inventory_quantity = inventory_quantity - #{quantity} WHERE product_code = #{productCode} AND inventory_quantity >= #{quantity}")
    int reduceInventory(@Param("productCode") String productCode, @Param("quantity") int quantity);
}
