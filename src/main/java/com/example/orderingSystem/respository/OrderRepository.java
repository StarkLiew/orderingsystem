package com.example.orderingSystem.respository;

import com.example.orderingSystem.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderRepository {

    @Insert("INSERT INTO orders (order_number, user_id, product_code, data_volume, amount, total_amount, order_status, created_at, updated_at) " +
            "VALUES (#{orderNumber}, #{userId}, #{productCode}, #{dataVolume}, #{amount}, #{totalAmount}, #{orderStatus}, #{createdAt}, #{updatedAt})")
    void insertOrder(Order order);

    @Update("UPDATE orders SET order_status = #{orderStatus}, updated_at = NOW() WHERE order_number = #{orderNumber}")
    void updateOrderStatus(@Param("orderNumber") String orderNumber, @Param("orderStatus") String orderStatus);

    @Select("SELECT * FROM orders WHERE order_number = #{orderNumber}")
    Order findOrderByOrderNumber(@Param("orderNumber") String orderNumber);

    @Select("SELECT * FROM orders WHERE order_status = 'pending' AND created_at < NOW() - INTERVAL 30 MINUTE")
    List<Order> findPendingOrdersOlderThan30Minutes();
}
